package com.cg.maven.tutorial.controller.admin;

import com.cg.maven.tutorial.model.PostContent;
import com.cg.maven.tutorial.model.Post_Tag;
import com.cg.maven.tutorial.model.Tag;
import com.cg.maven.tutorial.service.PostContentService;
import com.cg.maven.tutorial.service.Post_TagService;
import com.cg.maven.tutorial.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class PostContentController extends AdminBaseController {


    private final String TERM = "Content Manager";

    @Autowired
    private PostContentService postContentService;

    @Autowired
    private TagService tagService;

    @Autowired
    private Post_TagService post_tagService;

    public void initBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        if (target.getClass() == PostContent.class) {

            // Đăng ký để chuyển đổi giữa các đối tượng multipart thành byte[]
            dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        }
    }

    @GetMapping("/blog/")
    public ModelAndView index() {

        Iterable<PostContent> postContents = postContentService.findAll();
        ModelAndView modelAndView = new ModelAndView("/admin/blog/index");
        modelAndView.addObject("postContents", postContents);

        modelAndView.addObject("title", TITLE_ADD);
        modelAndView.addObject("term", TERM);

        return modelAndView;
    }

    @GetMapping("/blog/add")
    public ModelAndView showAddForm() {

        List<Tag> tags = tagService.findAll();
        ModelAndView modelAndView = new ModelAndView("/admin/blog/add");
        modelAndView.addObject("postContent", new PostContent());
        modelAndView.addObject("tag", tags);
        modelAndView.addObject("action", ACTION_ADD);
        modelAndView.addObject("term", TERM);
        modelAndView.addObject("title", TITLE_ADD);

        return modelAndView;
    }

    @PostMapping("/blog/add")
    public ModelAndView saveAddForm(HttpServletRequest request, @ModelAttribute("postContent") PostContent postContent,@RequestParam("listTag")Long[] listTag) {
        //
        String uploadRootPath = request.getServletContext().getRealPath("upload");
        System.out.println("uploadRootPath=" + uploadRootPath);

        File uploadRootDir = new File(uploadRootPath);
        //
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        CommonsMultipartFile[] fileDatas = postContent.getFileImage();
        //
        Map<File, String> uploadedFiles = new HashMap();
        for (CommonsMultipartFile fileData : fileDatas) {
            String name = fileData.getOriginalFilename();
            System.out.println("Client File Name = " + name);

            if (name != null && name.length() > 0) {
                try {
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();
                    //
                    postContent.setImage(name);
                    System.out.println("Write file: " + serverFile);
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                }
            }
        }

        postContentService.save(postContent);

        PostContent postContents =postContentService.findId();

        for( int i=0; i<listTag.length; i++){
            System.out.println(listTag[i]+',');
            Post_Tag post_tag =new Post_Tag();
            post_tag.setTag( tagService.findById(listTag[i]));
            post_tag.setPostContent(postContents);
            post_tagService.save(post_tag);
        }


        ModelAndView modelAndView = new ModelAndView("/admin/blog/add");
        modelAndView.addObject("postContent", new PostContent());
        modelAndView.addObject("action", ACTION_ADD);
        modelAndView.addObject("term", TERM);
        modelAndView.addObject("title", TITLE_ADD);
        modelAndView.addObject("alert", ALERT_SUCCESS);

        modelAndView.addObject("message", ACTION_ADD_SUCCESS);

        return modelAndView;

    }

    @PostMapping("/blog/edit")
    public ModelAndView saveEditForm(HttpServletRequest request, @ModelAttribute("postContent") PostContent postContent, @RequestParam("listTag")Long[] listTag) {
        //
        String uploadRootPath = request.getServletContext().getRealPath("upload");
        System.out.println("uploadRootPath=" + uploadRootPath);

        File uploadRootDir = new File(uploadRootPath);
        //
        // Tạo thư mục gốc upload nếu nó không tồn tại.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        CommonsMultipartFile[] fileDatas = postContent.getFileImage();
        //
        if (fileDatas != null) {
            Map<File, String> uploadedFiles = new HashMap();
            for (CommonsMultipartFile fileData : fileDatas) {

                // Tên file gốc tại Client.
                String name = fileData.getOriginalFilename();
                System.out.println("Client File Name = " + name);

                if (name != null && name.length() > 0) {
                    try {
                        // Tạo file tại Server.
                        File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

                        // Luồng ghi dữ liệu vào file trên Server.
                        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                        stream.write(fileData.getBytes());
                        stream.close();
                        //
                        postContent.setImage(name);
                        System.out.println("Write file: " + serverFile);
                    } catch (Exception e) {
                        System.out.println("Error Write file: " + name);
                    }
                }
            }
        }
        //

        postContentService.save(postContent);

        //

        List<Tag> tagList = tagService.findTagByContentId(postContent.getId());
        for (int i=0; i<listTag.length;i++) {
            boolean checkAdd = false;
            for (int j = 0; j < tagList.size(); j++) {
                if (tagList.get(j).getId().equals(listTag[i])) {
                    checkAdd = true;
                }
            }
            if(!checkAdd){
                Post_Tag post_tag =new Post_Tag();
                post_tag.setTag( tagService.findById(listTag[i]));
                post_tag.setPostContent(postContent);
                post_tagService.save(post_tag);
            }
        }

        for (int i=0; i<tagList.size();i++) {
            boolean checkDelete = false;
            for (int j = 0; j < listTag.length; j++) {
                if (tagList.get(i).getId().equals(listTag[j])) {
                    checkDelete = true;
                }
            }
            if (!checkDelete){
                Post_Tag post_tag = post_tagService.findByIdByPost_tag(postContent.getId(),tagList.get(i).getId());
                post_tagService.remove(post_tag.getId());
            }
        }



        ModelAndView modelAndView = new ModelAndView("/admin/blog/add");
        modelAndView.addObject("postContent", postContent);
        modelAndView.addObject("action", ACTION_EDIT);
        modelAndView.addObject("term", TERM);
        modelAndView.addObject("title", TITLE_EDIT);
        modelAndView.addObject("alert", ALERT_SUCCESS);
        modelAndView.addObject("message", ACTION_EDIT_SUCCESS);
        //
        return modelAndView;
    }

    @GetMapping("/blog/edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        PostContent postContent = postContentService.findById(id);


        if (postContent != null) {
            List<Tag> tags = tagService.findAll();
            for (Tag tag : tags
                 ) {
                for (Post_Tag t: postContent.post_tags
                ) {
                    if (t.tag.getId()==tag.getId())
                        tag.setSelected(true);
                }

            }
            ModelAndView modelAndView = new ModelAndView("/admin/blog/add");
            modelAndView.addObject("postContent", postContent);
            modelAndView.addObject("tag", tags);
            modelAndView.addObject("action", ACTION_EDIT);
            modelAndView.addObject("term", TERM);
            modelAndView.addObject("title", TITLE_EDIT);
            return modelAndView;

        } else {

            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }

    }

    @GetMapping("/blog/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        PostContent postContent = postContentService.findById(id);
        if (postContent != null) {

            List<Tag> tags = tagService.findAll();
            ModelAndView modelAndView = new ModelAndView("/admin/blog/delete");
            modelAndView.addObject("postContent", postContent);
            modelAndView.addObject("tag", tags);
            modelAndView.addObject("action", ACTION_DELETE);
            modelAndView.addObject("term", TERM);
            modelAndView.addObject("title", TITLE_DELETE);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/blog/delete")
    public String deleteContent(@ModelAttribute("postContent") PostContent postContent){
        postContentService.remove(postContent.getId());
        return "redirect:/admin/blog/";
    }
}

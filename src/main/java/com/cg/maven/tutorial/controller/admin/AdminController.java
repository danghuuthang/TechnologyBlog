package com.cg.maven.tutorial.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController extends AdminBaseController{

    private final  String TERM = "Tag";
    private final  String TERM1 = "USER";

    @GetMapping("/")
    public ModelAndView tagDataTablesAjax(){

        ModelAndView modelAndView = new ModelAndView("/admin/tag/tag");
        modelAndView.addObject("title",TITLE_ADD);
        modelAndView.addObject("term",TERM);

        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView view(){

        ModelAndView modelAndView = new ModelAndView("/admin/user/post");
        modelAndView.addObject("title",TITLE_ADD);
        modelAndView.addObject("term",TERM);

        return modelAndView;
    }
}

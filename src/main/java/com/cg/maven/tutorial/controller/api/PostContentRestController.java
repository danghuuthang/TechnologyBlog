package com.cg.maven.tutorial.controller.api;


import com.cg.maven.tutorial.model.PostContent;
import com.cg.maven.tutorial.service.PostContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostContentRestController {
    @Autowired
    private PostContentService postContentService;

    @GetMapping("/blogs/")
    private ResponseEntity<List<PostContent>> listPostContentLines(){
        List<PostContent> products= postContentService.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<List<PostContent>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<PostContent>>(products, HttpStatus.OK);
    }
}

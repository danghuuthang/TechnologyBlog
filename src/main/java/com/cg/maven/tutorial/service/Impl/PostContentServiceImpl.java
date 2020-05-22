package com.cg.maven.tutorial.service.Impl;

import com.cg.maven.tutorial.model.PostContent;
import com.cg.maven.tutorial.repository.PostContentRepository;
import com.cg.maven.tutorial.service.PostContentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostContentServiceImpl implements PostContentService {

    @Autowired
    private PostContentRepository postContentRepository;

    @Override
    public List<PostContent> findAll() {
        return postContentRepository.findAll();
    }

    @Override
    public PostContent findById(Long id) {
        return postContentRepository.findById(id).get();
    }

    @Override
    public void save(PostContent Object) {
        postContentRepository.save(Object);
    }

    @Override
    public void remove(Long id) {
        postContentRepository.deleteById(id);
    }

    @Override
    public PostContent findId() {
        return postContentRepository.findId();
    }

}

package com.cg.maven.tutorial.service.Impl;

import com.cg.maven.tutorial.model.PostContent;
import com.cg.maven.tutorial.model.Post_Tag;
import com.cg.maven.tutorial.repository.Post_TagRepository;
import com.cg.maven.tutorial.service.Post_TagService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Post_TagServiceImpl implements Post_TagService {

    @Autowired
    private Post_TagRepository post_tagRepository;

    @Override
    public List<Post_Tag> findAll() {
        return post_tagRepository.findAll();
    }

    @Override
    public Post_Tag findById(Long id) {
        return post_tagRepository.findById(id).get();
    }

    @Override
    public void save(Post_Tag Object) {
        post_tagRepository.save(Object);
    }

    @Override
    public void remove(Long id) {
        post_tagRepository.deleteById(id);
    }

    @Override
    public PostContent findId() {
        return null;
    }

    @Override
    public Post_Tag findByIdByPost_tag(Long idP, Long idT) {
        return post_tagRepository.findByIdByPost_tag(idP,idT);
    }
}

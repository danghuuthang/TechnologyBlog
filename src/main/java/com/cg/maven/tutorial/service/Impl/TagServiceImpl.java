package com.cg.maven.tutorial.service.Impl;

import com.cg.maven.tutorial.model.PostContent;
import com.cg.maven.tutorial.model.Tag;
import com.cg.maven.tutorial.repository.TagRepository;
import com.cg.maven.tutorial.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findById(Long id) {
        return tagRepository.findById(id).get();
    }

    @Override
    public void save(Tag Object) {
        tagRepository.save(Object);
    }

    @Override
    public void remove(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public PostContent findId() {
        return null;
    }

    @Override
    public List<Tag> findTagByContentId(Long id) {
        return tagRepository.findTagByContentId(id);
    }
}

package com.cg.maven.tutorial.service;

import com.cg.maven.tutorial.model.PostContent;
import com.cg.maven.tutorial.model.Tag;

import java.util.List;

public interface IService<T> {
    List<T> findAll();

    T findById(Long id);

    void save(T Object);

    void remove(Long id);

    PostContent findId();


}

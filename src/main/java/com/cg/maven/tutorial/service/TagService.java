package com.cg.maven.tutorial.service;

import com.cg.maven.tutorial.model.Tag;
import com.cg.maven.tutorial.service.IService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagService extends IService<Tag> {
    List<Tag> findTagByContentId(@Param("idPostContent") Long idPostContent);
}

package com.cg.maven.tutorial.service;

import com.cg.maven.tutorial.model.Post_Tag;
import org.springframework.data.repository.query.Param;

public interface Post_TagService extends IService<Post_Tag> {
    Post_Tag findByIdByPost_tag(@Param("idP") Long idP,@Param("idT") Long idT);
}

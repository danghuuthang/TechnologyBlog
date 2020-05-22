package com.cg.maven.tutorial.repository;

import com.cg.maven.tutorial.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {
    @Query(value="select tags.* from postcontents inner join post_tag on postcontents.id=post_tag.post_id inner join tags on tags.id=post_tag.tag_id where postcontents.id= :idPostContent",nativeQuery=true)
    List<Tag> findTagByContentId(@Param("idPostContent") Long idPostContent);
}

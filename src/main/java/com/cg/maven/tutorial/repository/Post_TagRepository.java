package com.cg.maven.tutorial.repository;

import com.cg.maven.tutorial.model.Post_Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Post_TagRepository  extends JpaRepository<Post_Tag, Long> {

    @Query(value="SELECT post_tag.* FROM post_tag where post_tag.post_id= :idP and post_tag.tag_id= :idT", nativeQuery=true)
    Post_Tag findByIdByPost_tag(@Param("idP") Long idP,@Param("idT") Long idT);

}

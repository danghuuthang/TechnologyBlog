package com.cg.maven.tutorial.repository;

import com.cg.maven.tutorial.model.PostContent;
import com.cg.maven.tutorial.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostContentRepository extends JpaRepository<PostContent,Long> {
    @Override
    @Modifying
    @Query("update PostContent p set p.isDelete=1 where p.id=:id")
    void deleteById(@Param("id") Long id);

    @Query(value="SELECT * FROM blog.postcontents order by postcontents.id desc limit 1;",nativeQuery=true)
        PostContent findId();

}

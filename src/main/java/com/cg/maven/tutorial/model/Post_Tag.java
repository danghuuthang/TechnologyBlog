package com.cg.maven.tutorial.model;

import javax.persistence.*;

@Entity
@Table(name = "post_tag")
public class Post_Tag {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "tag_id")
    public Tag tag;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostContent postContent;

    public PostContent getPostContent() {
        return postContent;
    }

    public void setPostContent(PostContent postContent) {
        this.postContent = postContent;
    }

    public Post_Tag() {
    }


}

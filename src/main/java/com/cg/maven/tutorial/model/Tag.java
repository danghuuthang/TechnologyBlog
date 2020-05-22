package com.cg.maven.tutorial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tags")
@Where(clause = "isDelete = 0")
public class Tag {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="varchar(75)")
    private String title;

    @Column(columnDefinition="varchar(255)")
    private String metaTitle;

    @Column(columnDefinition="varchar(100)")
    private String slug;

    @Column(columnDefinition="LONGTEXT")
    private String content;

    @Transient
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    private short isDelete;

    @JsonIgnore
    @OneToMany(mappedBy = "tag",fetch = FetchType.EAGER )
    public Set<Post_Tag> post_tags;

    public Set<Post_Tag> getPost_tags() {
        return post_tags;
    }

    public void setPost_tags(Set<Post_Tag> post_tags) {
        this.post_tags = post_tags;
    }

    public Tag() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

package com.cg.maven.tutorial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "postcontents")
@Where(clause = "isDelete = 0")
public class PostContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="varchar(500)")
    private String title;

    @Column(columnDefinition="LONGTEXT")
    private String description;

    @Column(columnDefinition="LONGTEXT")
    private String content;

    private String image;

    private short isDelete;

    @JsonIgnore
    @OneToMany(mappedBy = "postContent",fetch = FetchType.EAGER)
    public Set<Post_Tag> post_tags;

    public Set<Post_Tag> getPost_tags() {
        return post_tags;
    }

//    public Set<Tag> getTags() {
//        Set<Tag> rt = new HashSet<>();
//        for (Post_Tag t: getPost_tags()
//             ) {
//            rt.add(t.tag);
//        }
//        return rt;
//    }


    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Transient
    private Set<Tag> tags;

    public void setPost_tags(Set<Post_Tag> post_tags) {
        this.post_tags = post_tags;
    }

    public CommonsMultipartFile[] getFileImage() {
        return fileImage;
    }

    public void setFileImage(CommonsMultipartFile[] fileImage) {
        this.fileImage = fileImage;
    }

    @Transient
    @JsonIgnore
    private CommonsMultipartFile[] fileImage;

    public PostContent() {
    }

    public PostContent(Long id, String title, String description, String content, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

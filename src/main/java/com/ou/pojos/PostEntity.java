package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Post", schema = "TourismManagement")
public class PostEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "post_id")
    private int postId;
    @Basic
    @Column(name = "post_title")
    private String postTitle;
    @Basic
    @Column(name = "post_slug")
    private String postSlug;
    @Basic
    @Column(name = "post_content")
    private String postContent;
    @Basic
    @Column(name = "post_cover_page")
    private String postCoverPage;
    @Basic
    @Column(name = "acc_id")
    private int accId;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostSlug() {
        return postSlug;
    }

    public void setPostSlug(String postSlug) {
        this.postSlug = postSlug;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostCoverPage() {
        return postCoverPage;
    }

    public void setPostCoverPage(String postCoverPage) {
        this.postCoverPage = postCoverPage;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostEntity that = (PostEntity) o;
        return postId == that.postId && accId == that.accId && Objects.equals(postTitle, that.postTitle) && Objects.equals(postSlug, that.postSlug) && Objects.equals(postContent, that.postContent) && Objects.equals(postCoverPage, that.postCoverPage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, postTitle, postSlug, postContent, postCoverPage, accId);
    }
}

package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Post")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @Column(name="post_title")
    private String postTitle;

    @Column(name="post_slug")
    private String postSlug;

    @Column(name = "post_content")
    private String postContent;

    @Column(name = "post_cover_page")
    private String postCoverPage;

    @Column(name="post_is_active")
    private Boolean postIsActive;

    @ManyToOne
    @JoinColumn(name = "acc_id")
    private Account account;

    @OneToMany(mappedBy = "post")
    private List<News> news;

    @OneToMany(mappedBy = "post")
    private List<Tour> tours;

    @OneToMany(mappedBy = "post")
    private List<PostComment> postComments;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
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

    public Boolean getPostIsActive() {
        return postIsActive;
    }

    public void setPostIsActive(Boolean postIsActive) {
        this.postIsActive = postIsActive;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }
}

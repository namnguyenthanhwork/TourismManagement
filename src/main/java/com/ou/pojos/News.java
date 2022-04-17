package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="News")
public class News implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private Integer newsId;

    @Column(name = "news_created_date")
    private Date newsCreatedDate;

    @Column(name = "news_like_amount")
    private Integer newsLikeAmount;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private Post post;

    @OneToMany(mappedBy = "news")
    private List<NewsLike> newsLikes;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Date getNewsCreatedDate() {
        return newsCreatedDate;
    }

    public void setNewsCreatedDate(Date newsCreatedDate) {
        this.newsCreatedDate = newsCreatedDate;
    }

    public Integer getNewsLikeAmount() {
        return newsLikeAmount;
    }

    public void setNewsLikeAmount(Integer newsLikeAmount) {
        this.newsLikeAmount = newsLikeAmount;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<NewsLike> getNewsLikes() {
        return newsLikes;
    }

    public void setNewsLikes(List<NewsLike> newsLikes) {
        this.newsLikes = newsLikes;
    }
}

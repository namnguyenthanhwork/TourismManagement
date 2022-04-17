package com.ou.pojos;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "NewsLike")
public class NewsLike implements Serializable {
    @EmbeddedId
    private NewsLikeID newsLikeId;

    @Column(name = "like_active")
    private Integer likeActive;

    public NewsLikeID getNewsLikeId() {
        return newsLikeId;
    }

    public void setNewsLikeId(NewsLikeID newsLikeId) {
        this.newsLikeId = newsLikeId;
    }

    public Integer getLikeActive() {
        return likeActive;
    }

    public void setLikeActive(Integer likeActive) {
        this.likeActive = likeActive;
    }
}

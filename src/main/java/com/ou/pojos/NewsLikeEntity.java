package com.ou.pojos;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "NewsLike", schema = "TourismManagement")
@IdClass(NewsLikeEntityPK.class)
public class NewsLikeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "news_id")
    private int newsId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "acc_id")
    private int accId;
    @Basic
    @Column(name = "like_active")
    private Byte likeActive;

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public Byte getLikeActive() {
        return likeActive;
    }

    public void setLikeActive(Byte likeActive) {
        this.likeActive = likeActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsLikeEntity that = (NewsLikeEntity) o;
        return newsId == that.newsId && accId == that.accId && Objects.equals(likeActive, that.likeActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, accId, likeActive);
    }
}
package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "NewsLike", schema = "TourismManagement")
@IdClass(NewsLikeEntityPK.class)
public class NewsLikeEntity implements Serializable {
    @Id
    @Column(name = "news_id")
    private int newsId;
    @Id
    @Column(name = "acc_id")
    private int accId;

    @Basic
    @Column(name = "like_status")
    private boolean likeStatus;

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

    public boolean getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(boolean likeStatus) {
        this.likeStatus = likeStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsLikeEntity that = (NewsLikeEntity) o;
        return newsId == that.newsId && accId == that.accId && likeStatus==that.likeStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, accId, likeStatus);
    }
}

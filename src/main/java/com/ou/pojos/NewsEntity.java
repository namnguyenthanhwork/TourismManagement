package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "News", schema = "TourismManagement")
public class NewsEntity implements Serializable {
    @Id
    @Column(name = "news_id")
    private int newsId;
    @Basic
    @Column(name = "news_created_date")
    private Timestamp newsCreatedDate;
    @Basic
    @Column(name = "news_like_amount")
    private Integer newsLikeAmount;

    @Basic
    @Column(name = "news_description", length = Integer.MAX_VALUE)
    private String newsDescription;

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public Timestamp getNewsCreatedDate() {
        return newsCreatedDate;
    }

    public void setNewsCreatedDate(Timestamp newsCreatedDate) {
        this.newsCreatedDate = newsCreatedDate;
    }

    public Integer getNewsLikeAmount() {
        return newsLikeAmount;
    }

    public void setNewsLikeAmount(Integer newsLikeAmount) {
        this.newsLikeAmount = newsLikeAmount;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsEntity that = (NewsEntity) o;
        return newsId == that.newsId && Objects.equals(newsCreatedDate, that.newsCreatedDate) &&
                Objects.equals(newsLikeAmount, that.newsLikeAmount) && Objects.equals(newsDescription,  that.newsDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, newsCreatedDate, newsLikeAmount, newsDescription);
    }
}

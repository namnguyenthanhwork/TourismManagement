package com.ou.pojos;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class NewsLikeEntityPK implements Serializable {
    @Column(name = "news_id")
    @Id
    private int newsId;
    @Column(name = "acc_id")
    @Id
    private int accId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsLikeEntityPK that = (NewsLikeEntityPK) o;
        return newsId == that.newsId && accId == that.accId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, accId);
    }
}

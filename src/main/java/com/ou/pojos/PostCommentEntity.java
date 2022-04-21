package com.ou.pojos;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "PostComment", schema = "TourismManagement")
public class PostCommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cmt_id")
    private int cmtId;
    @Basic
    @Column(name = "cmt_created_date")
    private Timestamp cmtCreatedDate;
    @Basic
    @Column(name = "cmt_content")
    private String cmtContent;
    @Basic
    @Column(name = "acc_id")
    private int accId;
    @Basic
    @Column(name = "post_id")
    private int postId;

    public int getCmtId() {
        return cmtId;
    }

    public void setCmtId(int cmtId) {
        this.cmtId = cmtId;
    }

    public Timestamp getCmtCreatedDate() {
        return cmtCreatedDate;
    }

    public void setCmtCreatedDate(Timestamp cmtCreatedDate) {
        this.cmtCreatedDate = cmtCreatedDate;
    }

    public String getCmtContent() {
        return cmtContent;
    }

    public void setCmtContent(String cmtContent) {
        this.cmtContent = cmtContent;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostCommentEntity that = (PostCommentEntity) o;
        return cmtId == that.cmtId && accId == that.accId && postId == that.postId && Objects.equals(cmtCreatedDate, that.cmtCreatedDate) && Objects.equals(cmtContent, that.cmtContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cmtId, cmtCreatedDate, cmtContent, accId, postId);
    }
}

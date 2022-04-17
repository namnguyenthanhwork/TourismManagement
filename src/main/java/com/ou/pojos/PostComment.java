package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PostComment")
public class PostComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="cmt_id")
    private Integer cmtId;

    @Column(name = "cmt_date_created")
    private Date cmtDateCreated;

    @Column(name = "cmt_content")
    private String cmtContent;

    @ManyToOne
    @JoinColumn(name = "acc_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Integer getCmtId() {
        return cmtId;
    }

    public void setCmtId(Integer cmtId) {
        this.cmtId = cmtId;
    }

    public Date getCmtDateCreated() {
        return cmtDateCreated;
    }

    public void setCmtDateCreated(Date cmtDateCreated) {
        this.cmtDateCreated = cmtDateCreated;
    }

    public String getCmtContent() {
        return cmtContent;
    }

    public void setCmtContent(String cmtContent) {
        this.cmtContent = cmtContent;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

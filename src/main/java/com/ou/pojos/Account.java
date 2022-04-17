package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="acc_id")
    private Integer accId;

    @Column(name="acc_username")
    private String accUsername;

    @Column(name="acc_password")
    private String accPassword;

    @Column(name="acc_first_name")
    private String accFirstName;

    @Column(name="acc_last_name")
    private String accLastName;

    @Column(name="acc_sex")
    private Boolean accSex;

    @Column(name = "acc_id_card")
    private String accIdCard;

    @Column(name="acc_phone_number")
    private String accPhoneNumber;

    @Column(name="acc_date_of_birth")
    private Date accDateOfBirth;

    @Column(name="acc_joined_date")
    private Date accJoinedDate;

    @Column(name="acc_avatar")
    private String accAvatar;

    @Column(name="acc_last_access")
    private Date accLastAccess;

    @Column(name="acc_is_active")
    private Boolean accIsActive;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "account")
    private List<Post> posts;

    @OneToMany(mappedBy = "account")
    private List<Bill> bills;

    @OneToMany(mappedBy = "account")
    private List<PostComment> postComments;

    @OneToMany(mappedBy = "account")
    private List<NewsLike> newsLikes;

    @OneToMany(mappedBy = "account")
    private List<TourRating> tourRatings;

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public String getAccUsername() {
        return accUsername;
    }

    public void setAccUsername(String accUsername) {
        this.accUsername = accUsername;
    }

    public String getAccPassword() {
        return accPassword;
    }

    public void setAccPassword(String accPassword) {
        this.accPassword = accPassword;
    }

    public String getAccFirstName() {
        return accFirstName;
    }

    public void setAccFirstName(String accFirstName) {
        this.accFirstName = accFirstName;
    }

    public String getAccLastName() {
        return accLastName;
    }

    public void setAccLastName(String accLastName) {
        this.accLastName = accLastName;
    }

    public Boolean getAccSex() {
        return accSex;
    }

    public void setAccSex(Boolean accSex) {
        this.accSex = accSex;
    }

    public String getAccIdCard() {
        return accIdCard;
    }

    public void setAccIdCard(String accIdCard) {
        this.accIdCard = accIdCard;
    }

    public String getAccPhoneNumber() {
        return accPhoneNumber;
    }

    public void setAccPhoneNumber(String accPhoneNumber) {
        this.accPhoneNumber = accPhoneNumber;
    }

    public Date getAccDateOfBirth() {
        return accDateOfBirth;
    }

    public void setAccDateOfBirth(Date accDateOfBirth) {
        this.accDateOfBirth = accDateOfBirth;
    }

    public Date getAccJoinedDate() {
        return accJoinedDate;
    }

    public void setAccJoinedDate(Date accJoinedDate) {
        this.accJoinedDate = accJoinedDate;
    }

    public String getAccAvatar() {
        return accAvatar;
    }

    public void setAccAvatar(String accAvatar) {
        this.accAvatar = accAvatar;
    }

    public Date getAccLastAccess() {
        return accLastAccess;
    }

    public void setAccLastAccess(Date accLastAccess) {
        this.accLastAccess = accLastAccess;
    }

    public Boolean getAccIsActive() {
        return accIsActive;
    }

    public void setAccIsActive(Boolean accIsActive) {
        this.accIsActive = accIsActive;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }

    public List<NewsLike> getNewsLikes() {
        return newsLikes;
    }

    public void setNewsLikes(List<NewsLike> newsLikes) {
        this.newsLikes = newsLikes;
    }

    public List<TourRating> getTourRatings() {
        return tourRatings;
    }

    public void setTourRatings(List<TourRating> tourRatings) {
        this.tourRatings = tourRatings;
    }
}

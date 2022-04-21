package com.ou.pojos;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Account", schema = "TourismManagement")
public class AccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "acc_id")
    private int accId;
    @Basic
    @Column(name = "acc_username")
    private String accUsername;
    @Basic
    @Column(name = "acc_password")
    private String accPassword;
    @Basic
    @Column(name = "acc_first_name")
    private String accFirstName;
    @Basic
    @Column(name = "acc_last_name")
    private String accLastName;
    @Basic
    @Column(name = "acc_sex")
    private Byte accSex;
    @Basic
    @Column(name = "acc_id_card")
    private String accIdCard;
    @Basic
    @Column(name = "acc_phone_number")
    private String accPhoneNumber;
    @Basic
    @Column(name = "acc_date_of_birth")
    private Timestamp accDateOfBirth;
    @Basic
    @Column(name = "acc_joined_date")
    private Timestamp accJoinedDate;
    @Basic
    @Column(name = "acc_avatar")
    private String accAvatar;
    @Basic
    @Column(name = "acc_last_access")
    private Timestamp accLastAccess;
    @Basic
    @Column(name = "acc_is_active")
    private byte accIsActive;
    @Basic
    @Column(name = "role_id")
    private int roleId;

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
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

    public Byte getAccSex() {
        return accSex;
    }

    public void setAccSex(Byte accSex) {
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

    public Timestamp getAccDateOfBirth() {
        return accDateOfBirth;
    }

    public void setAccDateOfBirth(Timestamp accDateOfBirth) {
        this.accDateOfBirth = accDateOfBirth;
    }

    public Timestamp getAccJoinedDate() {
        return accJoinedDate;
    }

    public void setAccJoinedDate(Timestamp accJoinedDate) {
        this.accJoinedDate = accJoinedDate;
    }

    public String getAccAvatar() {
        return accAvatar;
    }

    public void setAccAvatar(String accAvatar) {
        this.accAvatar = accAvatar;
    }

    public Timestamp getAccLastAccess() {
        return accLastAccess;
    }

    public void setAccLastAccess(Timestamp accLastAccess) {
        this.accLastAccess = accLastAccess;
    }

    public byte getAccIsActive() {
        return accIsActive;
    }

    public void setAccIsActive(byte accIsActive) {
        this.accIsActive = accIsActive;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return accId == that.accId && accIsActive == that.accIsActive && roleId == that.roleId && Objects.equals(accUsername, that.accUsername) && Objects.equals(accPassword, that.accPassword) && Objects.equals(accFirstName, that.accFirstName) && Objects.equals(accLastName, that.accLastName) && Objects.equals(accSex, that.accSex) && Objects.equals(accIdCard, that.accIdCard) && Objects.equals(accPhoneNumber, that.accPhoneNumber) && Objects.equals(accDateOfBirth, that.accDateOfBirth) && Objects.equals(accJoinedDate, that.accJoinedDate) && Objects.equals(accAvatar, that.accAvatar) && Objects.equals(accLastAccess, that.accLastAccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accId, accUsername, accPassword, accFirstName, accLastName, accSex, accIdCard, accPhoneNumber, accDateOfBirth, accJoinedDate, accAvatar, accLastAccess, accIsActive, roleId);
    }
}

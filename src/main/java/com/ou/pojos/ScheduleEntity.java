package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Schedule", schema = "TourismManagement")
public class ScheduleEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sche_id")
    private int scheId;
    @Basic
    @Column(name = "sche_title")
    private String scheTitle;
    @Basic
    @Column(name = "sche_slug")
    private String scheSlug;
    @Basic
    @Column(name = "sche_content")
    private String scheContent;
    @Basic
    @Column(name = "tour_id")
    private int tourId;

    public int getScheId() {
        return scheId;
    }

    public void setScheId(int scheId) {
        this.scheId = scheId;
    }

    public String getScheTitle() {
        return scheTitle;
    }

    public void setScheTitle(String scheTitle) {
        this.scheTitle = scheTitle;
    }

    public String getScheSlug() {
        return scheSlug;
    }

    public void setScheSlug(String scheSlug) {
        this.scheSlug = scheSlug;
    }

    public String getScheContent() {
        return scheContent;
    }

    public void setScheContent(String scheContent) {
        this.scheContent = scheContent;
    }


    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleEntity that = (ScheduleEntity) o;
        return scheId == that.scheId && tourId == that.tourId && Objects.equals(scheTitle, that.scheTitle) && Objects.equals(scheSlug, that.scheSlug) && Objects.equals(scheContent, that.scheContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheId, scheTitle, scheSlug, scheContent, tourId);
    }
}

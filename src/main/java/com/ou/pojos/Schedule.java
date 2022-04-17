package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Schedule")
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sche_id")
    private Integer scheId;

    @Column(name = "sche_title")
    private String scheTitle;

    @Column(name = "sche_slug")
    private String scheSlug;

    @Column(name = "sche_content")
    private String scheContent;

    @Column(name = "sche_is_active")
    private Boolean scheIsActive;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    public Integer getScheId() {
        return scheId;
    }

    public void setScheId(Integer scheId) {
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

    public Boolean getScheIsActive() {
        return scheIsActive;
    }

    public void setScheIsActive(Boolean scheIsActive) {
        this.scheIsActive = scheIsActive;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }


}

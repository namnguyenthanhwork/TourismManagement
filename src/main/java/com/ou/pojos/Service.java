package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Service")
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serv_id")
    private Integer servId;

    @Column(name = "serv_title")
    private String servTitle;

    @Column(name = "serv_slug")
    private String servSlug;

    @Column(name = "serv_content")
    private String servContent;

    @Column(name = "serv_is_active")
    private Boolean servIsActive;

    @OneToMany(mappedBy = "service")
    private List<TourService> tourServices;

    public Integer getServId() {
        return servId;
    }

    public void setServId(Integer servId) {
        this.servId = servId;
    }

    public String getServTitle() {
        return servTitle;
    }

    public void setServTitle(String servTitle) {
        this.servTitle = servTitle;
    }

    public String getServSlug() {
        return servSlug;
    }

    public void setServSlug(String servSlug) {
        this.servSlug = servSlug;
    }

    public String getServContent() {
        return servContent;
    }

    public void setServContent(String servContent) {
        this.servContent = servContent;
    }

    public Boolean getServIsActive() {
        return servIsActive;
    }

    public void setServIsActive(Boolean servIsActive) {
        this.servIsActive = servIsActive;
    }

    public List<TourService> getTourServices() {
        return tourServices;
    }

    public void setTourServices(List<TourService> tourServices) {
        this.tourServices = tourServices;
    }
}

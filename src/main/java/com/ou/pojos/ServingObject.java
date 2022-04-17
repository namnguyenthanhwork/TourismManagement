package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ServingObject")
public class ServingObject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "svo_id")
    private Integer svoId;

    @Column(name = "svo_name")
    private String svoName;

    @Column(name = "svo_slug")
    private String svoSlug;

    @Column(name = "svo_is_active")
    private Boolean svoIsActive;

    @OneToMany(mappedBy = "servingObject")
    private List<TourServingObject> tourServingObjects;

    public Integer getSvoId() {
        return svoId;
    }

    public void setSvoId(Integer svoId) {
        this.svoId = svoId;
    }

    public String getSvoName() {
        return svoName;
    }

    public void setSvoName(String svoName) {
        this.svoName = svoName;
    }

    public String getSvoSlug() {
        return svoSlug;
    }

    public void setSvoSlug(String svoSlug) {
        this.svoSlug = svoSlug;
    }

    public Boolean getSvoIsActive() {
        return svoIsActive;
    }

    public void setSvoIsActive(Boolean svoIsActive) {
        this.svoIsActive = svoIsActive;
    }

    public List<TourServingObject> getTourServingObjects() {
        return tourServingObjects;
    }

    public void setTourServingObjects(List<TourServingObject> tourServingObjects) {
        this.tourServingObjects = tourServingObjects;
    }
}

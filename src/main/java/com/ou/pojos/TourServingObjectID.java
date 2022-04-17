package com.ou.pojos;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class TourServingObjectID implements Serializable {
    @Column(name = "tour_id")
    private Integer tourId;

    @Column(name = "svo_id")
    private Integer svoId;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "svo_id")
    private ServingObject servingObject;

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public Integer getSvoId() {
        return svoId;
    }

    public void setSvoId(Integer svoId) {
        this.svoId = svoId;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public ServingObject getServingObject() {
        return servingObject;
    }

    public void setServingObject(ServingObject servingObject) {
        this.servingObject = servingObject;
    }
}

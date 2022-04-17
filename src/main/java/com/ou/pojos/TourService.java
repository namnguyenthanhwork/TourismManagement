package com.ou.pojos;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TourService")
public class TourService implements Serializable {
    @EmbeddedId
    private TourServiceID tourServiceId;

    public TourServiceID getTourServiceId() {
        return tourServiceId;
    }

    public void setTourServiceId(TourServiceID tourServiceId) {
        this.tourServiceId = tourServiceId;
    }
}

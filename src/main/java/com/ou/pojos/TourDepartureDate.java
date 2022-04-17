package com.ou.pojos;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TourDepartureDate")
public class TourDepartureDate implements Serializable {
    @EmbeddedId
    private TourDepartureDateID tourDepartureDateId;
    @Column(name = "tour_amount")
    private Integer tourAmount;

    public TourDepartureDateID getTourDepartureDateId() {
        return tourDepartureDateId;
    }

    public void setTourDepartureDateId(TourDepartureDateID tourDepartureDateId) {
        this.tourDepartureDateId = tourDepartureDateId;
    }

    public Integer getTourAmount() {
        return tourAmount;
    }

    public void setTourAmount(Integer tourAmount) {
        this.tourAmount = tourAmount;
    }
}

package com.ou.pojos;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class TourServingObject implements Serializable {
    @EmbeddedId
    private TourServingObjectID tourServingObjectId;

    @Column(name = "tour_price")
    private BigDecimal tourPrice;

    public TourServingObjectID getTourServingObjectId() {
        return tourServingObjectId;
    }

    public void setTourServingObjectId(TourServingObjectID tourServingObjectId) {
        this.tourServingObjectId = tourServingObjectId;
    }

    public BigDecimal getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(BigDecimal tourPrice) {
        this.tourPrice = tourPrice;
    }
}

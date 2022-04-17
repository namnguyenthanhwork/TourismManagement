package com.ou.pojos;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TourRating")
public class TourRating implements Serializable {
    @EmbeddedId
    private TourRatingID tourRatingI;

    @Column(name = "rate_amount")
    private Integer rateAmount;

    public TourRatingID getTourRatingI() {
        return tourRatingI;
    }

    public void setTourRatingI(TourRatingID tourRatingI) {
        this.tourRatingI = tourRatingI;
    }

    public Integer getRateAmount() {
        return rateAmount;
    }

    public void setRateAmount(Integer rateAmount) {
        this.rateAmount = rateAmount;
    }
}

package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TourRating", schema = "TourismManagement")
@IdClass(TourRatingEntityPK.class)
public class TourRatingEntity implements Serializable {

    @Id
    @Column(name = "tour_id")
    private int tourId;

    @Id
    @Column(name = "acc_id")
    private int accId;
    @Basic
    @Column(name = "rate_amount")
    private Integer rateAmount = 0;

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public Integer getRateAmount() {
        return rateAmount;
    }

    public void setRateAmount(Integer rateAmount) {
        this.rateAmount = rateAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourRatingEntity that = (TourRatingEntity) o;
        return tourId == that.tourId && accId == that.accId && Objects.equals(rateAmount, that.rateAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId, accId, rateAmount);
    }
}

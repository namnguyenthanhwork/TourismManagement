package com.ou.pojos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TourRatingEntityPK implements Serializable {
    @Column(name = "tour_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tourId;
    @Column(name = "acc_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourRatingEntityPK that = (TourRatingEntityPK) o;
        return tourId == that.tourId && accId == that.accId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId, accId);
    }
}

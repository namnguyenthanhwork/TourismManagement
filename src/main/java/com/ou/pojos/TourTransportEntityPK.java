package com.ou.pojos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TourTransportEntityPK implements Serializable {
    @Column(name = "tour_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tourId;
    @Column(name = "tran_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tranId;

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getTranId() {
        return tranId;
    }

    public void setTranId(int tranId) {
        this.tranId = tranId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourTransportEntityPK that = (TourTransportEntityPK) o;
        return tourId == that.tourId && tranId == that.tranId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId, tranId);
    }
}

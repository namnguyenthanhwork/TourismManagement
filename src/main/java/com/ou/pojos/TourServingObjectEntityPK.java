package com.ou.pojos;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TourServingObjectEntityPK implements Serializable {
    @Column(name = "tour_id")
    @Id
    private int tourId;
    @Column(name = "svo_id")
    @Id
    private int svoId;

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getSvoId() {
        return svoId;
    }

    public void setSvoId(int svoId) {
        this.svoId = svoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourServingObjectEntityPK that = (TourServingObjectEntityPK) o;
        return tourId == that.tourId && svoId == that.svoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId, svoId);
    }
}

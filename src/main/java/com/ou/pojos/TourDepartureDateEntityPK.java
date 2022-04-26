package com.ou.pojos;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TourDepartureDateEntityPK implements Serializable {
    @Column(name = "tour_id")
    @Id
    private int tourId;
    @Column(name = "dpt_id")
    @Id
    private int dptId;

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getDptId() {
        return dptId;
    }

    public void setDptId(int dptId) {
        this.dptId = dptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourDepartureDateEntityPK that = (TourDepartureDateEntityPK) o;
        return tourId == that.tourId && dptId == that.dptId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId, dptId);
    }
}

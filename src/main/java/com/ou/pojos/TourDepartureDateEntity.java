package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TourDepartureDate", schema = "TourismManagement")
@IdClass(TourDepartureDateEntityPK.class)
public class TourDepartureDateEntity implements Serializable {

    @Id
    @Column(name = "tour_id")
    private int tourId;

    @Id
    @Column(name = "dpt_id")
    private int dptId;
    @Basic
    @Column(name = "tour_amount")
    private Integer tourAmount = 1;

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

    public Integer getTourAmount() {
        return tourAmount;
    }

    public void setTourAmount(Integer tourAmount) {
        this.tourAmount = tourAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourDepartureDateEntity that = (TourDepartureDateEntity) o;
        return tourId == that.tourId && dptId == that.dptId && Objects.equals(tourAmount, that.tourAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId, dptId, tourAmount);
    }
}

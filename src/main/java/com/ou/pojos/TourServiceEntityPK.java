package com.ou.pojos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TourServiceEntityPK implements Serializable {
    @Column(name = "tour_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tourId;
    @Column(name = "serv_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int servId;

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getServId() {
        return servId;
    }

    public void setServId(int servId) {
        this.servId = servId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourServiceEntityPK that = (TourServiceEntityPK) o;
        return tourId == that.tourId && servId == that.servId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId, servId);
    }
}

package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TourService", schema = "TourismManagement")
@IdClass(TourServiceEntityPK.class)
public class TourServiceEntity implements Serializable {

    @Id
    @Column(name = "tour_id")
    private int tourId;

    @Id
    @Column(name = "serv_id")
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
        TourServiceEntity that = (TourServiceEntity) o;
        return tourId == that.tourId && servId == that.servId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId, servId);
    }
}

package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TourTransport", schema = "TourismManagement")
@IdClass(TourTransportEntityPK.class)
public class TourTransportEntity implements Serializable {
    @Id
    @Column(name = "tour_id")
    private int tourId;
    @Id
    @Column(name = "tran_id")
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
        TourTransportEntity that = (TourTransportEntity) o;
        return tourId == that.tourId && tranId == that.tranId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId, tranId);
    }
}

package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "TourServingObject", schema = "TourismManagement")
public class TourServingObjectEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tsvo_id")
    private int tsvoId;

    @Column(name = "tour_id")
    private int tourId;

    @Column(name = "svo_id")
    private int svoId;
    @Basic
    @Column(name = "tour_price")
    private BigDecimal tourPrice = BigDecimal.ZERO;;

    public int getTsvoId(){return tsvoId;}

    public void setTsvoId(int tsvoId){
        this.tsvoId=tsvoId;
    }

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

    public BigDecimal getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(BigDecimal tourPrice) {
        this.tourPrice = tourPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourServingObjectEntity that = (TourServingObjectEntity) o;
        return tsvoId==that.tsvoId && tourId == that.tourId && svoId == that.svoId && Objects.equals(tourPrice, that.tourPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tsvoId, tourId, svoId, tourPrice);
    }
}

package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Tour", schema = "TourismManagement")
public class TourEntity implements Serializable {

    @Id
    @Column(name = "tour_id")
    private int tourId;
    @Basic
    @Column(name = "tour_average_rating")
    private Integer tourAverageRating;
    @Basic
    @Column(name = "sale_id")
    private Integer saleId;
    @Basic
    @Column(name = "cat_id")
    private int catId;

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public Integer getTourAverageRating() {
        return tourAverageRating;
    }

    public void setTourAverageRating(Integer tourAverageRating) {
        this.tourAverageRating = tourAverageRating;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourEntity that = (TourEntity) o;
        return tourId == that.tourId && catId == that.catId && Objects.equals(tourAverageRating, that.tourAverageRating) && Objects.equals(saleId, that.saleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId, tourAverageRating, saleId, catId);
    }
}

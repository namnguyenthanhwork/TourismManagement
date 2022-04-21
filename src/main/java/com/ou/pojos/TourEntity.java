package com.ou.pojos;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Tour", schema = "TourismManagement")
public class TourEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tour_id")
    private int tourId;
    @Basic
    @Column(name = "tour_average_rating")
    private Integer tourAverageRating;
    @Basic
    @Column(name = "tour_is_active")
    private byte tourIsActive;
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

    public byte getTourIsActive() {
        return tourIsActive;
    }

    public void setTourIsActive(byte tourIsActive) {
        this.tourIsActive = tourIsActive;
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
        return tourId == that.tourId && tourIsActive == that.tourIsActive && catId == that.catId && Objects.equals(tourAverageRating, that.tourAverageRating) && Objects.equals(saleId, that.saleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId, tourAverageRating, tourIsActive, saleId, catId);
    }
}

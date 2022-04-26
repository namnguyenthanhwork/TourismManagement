package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Thumbnail", schema = "TourismManagement")
public class ThumbnailEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "thum_id")
    private int thumId;
    @Basic
    @Column(name = "thum_image")
    private String thumImage;
    @Basic
    @Column(name = "tour_id")
    private int tourId;

    public int getThumId() {
        return thumId;
    }

    public void setThumId(int thumId) {
        this.thumId = thumId;
    }

    public String getThumImage() {
        return thumImage;
    }

    public void setThumImage(String thumImage) {
        this.thumImage = thumImage;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThumbnailEntity that = (ThumbnailEntity) o;
        return thumId == that.thumId && tourId == that.tourId && Objects.equals(thumImage, that.thumImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thumId, thumImage, tourId);
    }
}

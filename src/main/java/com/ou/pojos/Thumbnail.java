package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Thumbnail")
public class Thumbnail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thum_id")
    private Integer thumId;

    @Column(name = "thum_image")
    private String thumImage;

    @Column(name = "thum_is_active")
    private Boolean thumIsActive;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    public Integer getThumId() {
        return thumId;
    }

    public void setThumId(Integer thumId) {
        this.thumId = thumId;
    }

    public String getThumImage() {
        return thumImage;
    }

    public void setThumImage(String thumImage) {
        this.thumImage = thumImage;
    }

    public Boolean getThumIsActive() {
        return thumIsActive;
    }

    public void setThumIsActive(Boolean thumIsActive) {
        this.thumIsActive = thumIsActive;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}

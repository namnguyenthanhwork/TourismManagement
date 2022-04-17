package com.ou.pojos;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TourNote")
public class TourNote implements Serializable {
    @EmbeddedId
    private TourRatingID tourRatingId;

    public TourRatingID getTourRatingId() {
        return tourRatingId;
    }

    public void setTourRatingId(TourRatingID tourRatingId) {
        this.tourRatingId = tourRatingId;
    }
}

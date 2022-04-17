package com.ou.pojos;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TourTransport")
public class TourTransport implements Serializable {
    @EmbeddedId
    private TourTransportID tourTransportId;

    public TourTransportID getTourTransportId() {
        return tourTransportId;
    }

    public void setTourTransportId(TourTransportID tourTransportId) {
        this.tourTransportId = tourTransportId;
    }
}

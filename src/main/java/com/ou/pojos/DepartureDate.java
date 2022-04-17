package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "DepartureDate")
public class DepartureDate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dpt_id")
    private Integer dptId;

    @Column(name = "dpt_date")
    private Date dptDate;

    @Column(name = "dpt_is_acitve")
    private Boolean dptIsActive;

    @ManyToOne
    @JoinColumn(name = "fea_id")
    private Feature feature;

    @OneToMany(mappedBy = "departureDate")
    private List<TourDepartureDate> tourDepartureDates;

    public Integer getDptId() {
        return dptId;
    }

    public void setDptId(Integer dptId) {
        this.dptId = dptId;
    }

    public Date getDptDate() {
        return dptDate;
    }

    public void setDptDate(Date dptDate) {
        this.dptDate = dptDate;
    }

    public Boolean getDptIsActive() {
        return dptIsActive;
    }

    public void setDptIsActive(Boolean dptIsActive) {
        this.dptIsActive = dptIsActive;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public List<TourDepartureDate> getTourDepartureDates() {
        return tourDepartureDates;
    }

    public void setTourDepartureDates(List<TourDepartureDate> tourDepartureDates) {
        this.tourDepartureDates = tourDepartureDates;
    }
}

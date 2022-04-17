package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Feature")
public class Feature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fea_id")
    private Integer feaId;

    @Column(name="fea_name")
    private String feaName;

    @Column(name = "fea_slug")
    private String feaSlug;

    @Column(name = "fea_is_active")
    private Boolean feaIsActive;

    @OneToMany(mappedBy = "feature")
    private List<DepartureDate> departureDates;

    public Integer getFeaId() {
        return feaId;
    }

    public void setFeaId(Integer feaId) {
        this.feaId = feaId;
    }

    public String getFeaName() {
        return feaName;
    }

    public void setFeaName(String feaName) {
        this.feaName = feaName;
    }

    public String getFeaSlug() {
        return feaSlug;
    }

    public void setFeaSlug(String feaSlug) {
        this.feaSlug = feaSlug;
    }

    public Boolean getFeaIsActive() {
        return feaIsActive;
    }

    public void setFeaIsActive(Boolean feaIsActive) {
        this.feaIsActive = feaIsActive;
    }

    public List<DepartureDate> getDepartureDates() {
        return departureDates;
    }

    public void setDepartureDates(List<DepartureDate> departureDates) {
        this.departureDates = departureDates;
    }
}

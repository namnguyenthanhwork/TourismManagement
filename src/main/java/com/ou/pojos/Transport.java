package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Transport")
public class Transport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tran_id")
    private Integer tranId;

    @Column(name = "tran_name")
    private String tranName;

    @Column(name = "tran_slug")
    private String tranSlug;

    @Column(name = "tran_is_active")
    private Boolean tranIsActive;

    @OneToMany(mappedBy = "transport")
    private List<TourTransport> tourTransports;

    public Integer getTranId() {
        return tranId;
    }

    public void setTranId(Integer tranId) {
        this.tranId = tranId;
    }

    public String getTranName() {
        return tranName;
    }

    public void setTranName(String tranName) {
        this.tranName = tranName;
    }

    public String getTranSlug() {
        return tranSlug;
    }

    public void setTranSlug(String tranSlug) {
        this.tranSlug = tranSlug;
    }

    public Boolean getTranIsActive() {
        return tranIsActive;
    }

    public void setTranIsActive(Boolean tranIsActive) {
        this.tranIsActive = tranIsActive;
    }

    public List<TourTransport> getTourTransports() {
        return tourTransports;
    }

    public void setTourTransports(List<TourTransport> tourTransports) {
        this.tourTransports = tourTransports;
    }
}

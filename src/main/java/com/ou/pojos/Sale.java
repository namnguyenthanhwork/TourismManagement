package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Sale")
public class Sale implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Integer saleId;

    @Column(name = "sale_from_date")
    private Date saleFromDate;

    @Column(name = "sale_to_date")
    private Date saleToDate;

    @Column(name="sale_is_active")
    private Boolean saleIsActive;

    @ManyToOne
    @JoinColumn(name = "sper_id")
    private SalePercent salePercent;

    @OneToMany(mappedBy = "sale")
    private List<Tour> tours;

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Date getSaleFromDate() {
        return saleFromDate;
    }

    public void setSaleFromDate(Date saleFromDate) {
        this.saleFromDate = saleFromDate;
    }

    public Date getSaleToDate() {
        return saleToDate;
    }

    public void setSaleToDate(Date saleToDate) {
        this.saleToDate = saleToDate;
    }

    public Boolean getSaleIsActive() {
        return saleIsActive;
    }

    public void setSaleIsActive(Boolean saleIsActive) {
        this.saleIsActive = saleIsActive;
    }

    public SalePercent getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(SalePercent salePercent) {
        this.salePercent = salePercent;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }
}

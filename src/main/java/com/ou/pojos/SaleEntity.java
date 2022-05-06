package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Sale", schema = "TourismManagement")
public class SaleEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sale_id")
    private int saleId;
    @Basic
    @Column(name = "sale_from_date")
    private Timestamp saleFromDate;
    @Basic
    @Column(name = "sale_to_date")
    private Timestamp saleToDate;
    @Basic
    @Column(name = "sper_id")
    private int sperId;

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public Timestamp getSaleFromDate() {
        return saleFromDate;
    }

    public void setSaleFromDate(Timestamp saleFromDate) {
        this.saleFromDate = saleFromDate;
    }

    public Timestamp getSaleToDate() {
        return saleToDate;
    }

    public void setSaleToDate(Timestamp saleToDate) {
        this.saleToDate = saleToDate;
    }


    public int getSperId() {
        return sperId;
    }

    public void setSperId(int sperId) {
        this.sperId = sperId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleEntity that = (SaleEntity) o;
        return saleId == that.saleId && sperId == that.sperId && Objects.equals(saleFromDate, that.saleFromDate) && Objects.equals(saleToDate, that.saleToDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleId, saleFromDate, saleToDate, sperId);
    }
}

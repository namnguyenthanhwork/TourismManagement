package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "SalePercent", schema = "TourismManagement")
public class SalePercentEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sper_id")
    private int sperId;
    @Basic
    @Column(name = "sper_percent")
    private Integer sperPercent;

    public int getSperId() {
        return sperId;
    }

    public void setSperId(int sperId) {
        this.sperId = sperId;
    }

    public Integer getSperPercent() {
        return sperPercent;
    }

    public void setSperPercent(Integer sperPercent) {
        this.sperPercent = sperPercent;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalePercentEntity that = (SalePercentEntity) o;
        return sperId == that.sperId && Objects.equals(sperPercent, that.sperPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sperId, sperPercent);
    }
}

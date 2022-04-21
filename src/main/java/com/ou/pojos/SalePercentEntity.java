package com.ou.pojos;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SalePercent", schema = "TourismManagement")
public class SalePercentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sper_id")
    private int sperId;
    @Basic
    @Column(name = "sper_percent")
    private Integer sperPercent;
    @Basic
    @Column(name = "sper_is_active")
    private byte sperIsActive;

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

    public byte getSperIsActive() {
        return sperIsActive;
    }

    public void setSperIsActive(byte sperIsActive) {
        this.sperIsActive = sperIsActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalePercentEntity that = (SalePercentEntity) o;
        return sperId == that.sperId && sperIsActive == that.sperIsActive && Objects.equals(sperPercent, that.sperPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sperId, sperPercent, sperIsActive);
    }
}

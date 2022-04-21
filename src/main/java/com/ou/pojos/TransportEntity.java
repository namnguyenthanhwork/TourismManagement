package com.ou.pojos;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Transport", schema = "TourismManagement")
public class TransportEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tran_id")
    private int tranId;
    @Basic
    @Column(name = "tran_name")
    private String tranName;
    @Basic
    @Column(name = "tran_slug")
    private String tranSlug;
    @Basic
    @Column(name = "tran_is_active")
    private byte tranIsActive;

    public int getTranId() {
        return tranId;
    }

    public void setTranId(int tranId) {
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

    public byte getTranIsActive() {
        return tranIsActive;
    }

    public void setTranIsActive(byte tranIsActive) {
        this.tranIsActive = tranIsActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportEntity that = (TransportEntity) o;
        return tranId == that.tranId && tranIsActive == that.tranIsActive && Objects.equals(tranName, that.tranName) && Objects.equals(tranSlug, that.tranSlug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tranId, tranName, tranSlug, tranIsActive);
    }
}

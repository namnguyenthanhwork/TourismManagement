package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "PaymentType", schema = "TourismManagement")
public class PaymentTypeEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "payt_id")
    private int paytId;
    @Basic
    @Column(name = "payt_name")
    private String paytName;
    @Basic
    @Column(name = "payt_slug")
    private String paytSlug;

    public int getPaytId() {
        return paytId;
    }

    public void setPaytId(int paytId) {
        this.paytId = paytId;
    }

    public String getPaytName() {
        return paytName;
    }

    public void setPaytName(String paytName) {
        this.paytName = paytName;
    }

    public String getPaytSlug() {
        return paytSlug;
    }

    public void setPaytSlug(String paytSlug) {
        this.paytSlug = paytSlug;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentTypeEntity that = (PaymentTypeEntity) o;
        return paytId == that.paytId && Objects.equals(paytName, that.paytName) && Objects.equals(paytSlug, that.paytSlug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paytId, paytName, paytSlug);
    }
}

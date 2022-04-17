package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SalePercent")
public class SalePercent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sper_id")
    private Integer sperId;

    @Column(name = "sper_percent")
    private Float sperPercent;

    @Column(name = "sper_is_active")
    private Boolean sperIsActive;

    @OneToMany(mappedBy = "salePercent")
    private List<Sale> sales;

    public Integer getSperId() {
        return sperId;
    }

    public void setSperId(Integer sperId) {
        this.sperId = sperId;
    }

    public Float getSperPercent() {
        return sperPercent;
    }

    public void setSperPercent(Float sperPercent) {
        this.sperPercent = sperPercent;
    }

    public Boolean getSperIsActive() {
        return sperIsActive;
    }

    public void setSperIsActive(Boolean sperIsActive) {
        this.sperIsActive = sperIsActive;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}

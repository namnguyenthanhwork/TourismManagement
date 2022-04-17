package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PaymentType")
public class PaymentType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payt_id")
    private Integer paytId;

    @Column(name = "payt_name")
    private String paytname;

    @Column(name="payt_slug")
    private String paytSlug;

    @Column(name = "payt_is_active")
    private Boolean paytIsActive;

    @OneToMany(mappedBy = "paymentType")
    private List<Bill> bills;

    public Integer getPaytId() {
        return paytId;
    }

    public void setPaytId(Integer paytId) {
        this.paytId = paytId;
    }

    public String getPaytname() {
        return paytname;
    }

    public void setPaytname(String paytname) {
        this.paytname = paytname;
    }

    public String getPaytSlug() {
        return paytSlug;
    }

    public void setPaytSlug(String paytSlug) {
        this.paytSlug = paytSlug;
    }

    public Boolean getPaytIsActive() {
        return paytIsActive;
    }

    public void setPaytIsActive(Boolean paytIsActive) {
        this.paytIsActive = paytIsActive;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}

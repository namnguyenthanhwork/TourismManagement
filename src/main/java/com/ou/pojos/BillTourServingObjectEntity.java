package com.ou.pojos;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "BillTourServingObject", schema = "TourismManagement")
@IdClass(BillTourServingObjectEntityPK.class)
public class BillTourServingObjectEntity implements Serializable {
    @Id
    @Column(name = "bill_id")
    private int billId;
    @Id
    @Column(name = "tsvo_id")
    private int tsvoId;
    @Basic
    @Column(name = "tour_amount")
    private int tourAmount;

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getTsvoId() {
        return tsvoId;
    }

    public void setTsvoId(int tsvoId) {
        this.tsvoId = tsvoId;
    }

    public int getTourAmount() {
        return tourAmount;
    }

    public void setTourAmount(int tourAmount) {
        this.tourAmount = tourAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillTourServingObjectEntity that = (BillTourServingObjectEntity) o;
        return billId == that.billId && tsvoId == that.tsvoId && tourAmount == that.tourAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(billId, tsvoId, tourAmount);
    }

}

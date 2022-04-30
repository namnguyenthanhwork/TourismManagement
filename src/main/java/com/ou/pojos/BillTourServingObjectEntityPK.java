package com.ou.pojos;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BillTourServingObjectEntityPK implements Serializable {
    @Column(name = "bill_id")
    @Id
    private int billId;
    @Column(name = "tsvo_id")
    @Id
    private int tsvoId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillTourServingObjectEntityPK that = (BillTourServingObjectEntityPK) o;
        return billId == that.billId && tsvoId == that.tsvoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(billId, tsvoId);
    }
}

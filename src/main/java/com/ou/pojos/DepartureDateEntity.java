package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "DepartureDate", schema = "TourismManagement")
public class DepartureDateEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "dpt_id")
    private int dptId;
    @Basic
    @Column(name = "dpt_date")
    private Timestamp dptDate;
    @Basic
    @Column(name = "fea_id")
    private int feaId;

    public int getDptId() {
        return dptId;
    }

    public void setDptId(int dptId) {
        this.dptId = dptId;
    }

    public Timestamp getDptDate() {
        return dptDate;
    }

    public void setDptDate(Timestamp dptDate) {
        this.dptDate = dptDate;
    }

    public int getFeaId() {
        return feaId;
    }

    public void setFeaId(int feaId) {
        this.feaId = feaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartureDateEntity that = (DepartureDateEntity) o;
        return dptId == that.dptId && feaId == that.feaId && Objects.equals(dptDate, that.dptDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dptId, dptDate, feaId);
    }
}

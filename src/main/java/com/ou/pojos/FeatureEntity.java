package com.ou.pojos;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Feature", schema = "TourismManagement")
public class FeatureEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "fea_id")
    private int feaId;
    @Basic
    @Column(name = "fea_name")
    private String feaName;
    @Basic
    @Column(name = "fea_slug")
    private String feaSlug;
    @Basic
    @Column(name = "fea_is_active")
    private byte feaIsActive;

    public int getFeaId() {
        return feaId;
    }

    public void setFeaId(int feaId) {
        this.feaId = feaId;
    }

    public String getFeaName() {
        return feaName;
    }

    public void setFeaName(String feaName) {
        this.feaName = feaName;
    }

    public String getFeaSlug() {
        return feaSlug;
    }

    public void setFeaSlug(String feaSlug) {
        this.feaSlug = feaSlug;
    }

    public byte getFeaIsActive() {
        return feaIsActive;
    }

    public void setFeaIsActive(byte feaIsActive) {
        this.feaIsActive = feaIsActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeatureEntity that = (FeatureEntity) o;
        return feaId == that.feaId && feaIsActive == that.feaIsActive && Objects.equals(feaName, that.feaName) && Objects.equals(feaSlug, that.feaSlug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feaId, feaName, feaSlug, feaIsActive);
    }
}

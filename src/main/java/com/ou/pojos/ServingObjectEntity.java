package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ServingObject", schema = "TourismManagement")
public class ServingObjectEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "svo_id")
    private int svoId;
    @Basic
    @Column(name = "svo_name")
    private String svoName;
    @Basic
    @Column(name = "svo_slug")
    private String svoSlug;

    public int getSvoId() {
        return svoId;
    }

    public void setSvoId(int svoId) {
        this.svoId = svoId;
    }

    public String getSvoName() {
        return svoName;
    }

    public void setSvoName(String svoName) {
        this.svoName = svoName;
    }

    public String getSvoSlug() {
        return svoSlug;
    }

    public void setSvoSlug(String svoSlug) {
        this.svoSlug = svoSlug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServingObjectEntity that = (ServingObjectEntity) o;
        return svoId == that.svoId && Objects.equals(svoName, that.svoName) && Objects.equals(svoSlug, that.svoSlug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(svoId, svoName, svoSlug);
    }
}

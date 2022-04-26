package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Category", schema = "TourismManagement")
public class CategoryEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cat_id")
    private int catId;
    @Basic
    @Column(name = "cat_name")
    private String catName;
    @Basic
    @Column(name = "cat_slug")
    private String catSlug;
    @Basic
    @Column(name = "stor_id")
    private int storId;

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatSlug() {
        return catSlug;
    }

    public void setCatSlug(String catSlug) {
        this.catSlug = catSlug;
    }


    public int getStorId() {
        return storId;
    }

    public void setStorId(int storId) {
        this.storId = storId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return catId == that.catId && storId == that.storId && Objects.equals(catName, that.catName) && Objects.equals(catSlug, that.catSlug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catId, catName, catSlug, storId);
    }
}

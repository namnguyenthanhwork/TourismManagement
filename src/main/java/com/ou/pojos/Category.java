package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Integer catId;

    @Column(name = "cat_name")
    private String catName;

    @Column(name = "cat_slug")
    private String catSlug;

    @Column(name = "cat_is_active")
    private String catIsActive;

    @ManyToOne
    @JoinColumn(name = "stor_id")
    private Storage storage;

    @OneToMany(mappedBy = "category")
    private List<Tour> tours;

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
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

    public String getCatIsActive() {
        return catIsActive;
    }

    public void setCatIsActive(String catIsActive) {
        this.catIsActive = catIsActive;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }
}

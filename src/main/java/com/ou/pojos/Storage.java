package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Storage")
public class Storage implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="stor_id")
    private Integer storId;

    @Column(name = "stor_name")
    private String storName;

    @Column(name = "stor_slug")
    private String storSlug;

    @Column(name = "stor_is_active")
    private Boolean storIsActive;

    @OneToMany(mappedBy = "storage")
    private List<Category> categories;

    public Integer getStorId() {
        return storId;
    }

    public void setStorId(Integer storId) {
        this.storId = storId;
    }

    public String getStorName() {
        return storName;
    }

    public void setStorName(String storName) {
        this.storName = storName;
    }

    public String getStorSlug() {
        return storSlug;
    }

    public void setStorSlug(String storSlug) {
        this.storSlug = storSlug;
    }

    public Boolean getStorIsActive() {
        return storIsActive;
    }

    public void setStorIsActive(Boolean storIsActive) {
        this.storIsActive = storIsActive;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}

package com.ou.pojos;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Service", schema = "TourismManagement")
public class ServiceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "serv_id")
    private int servId;
    @Basic
    @Column(name = "serv_title")
    private String servTitle;
    @Basic
    @Column(name = "serv_slug")
    private String servSlug;
    @Basic
    @Column(name = "serv_content")
    private String servContent;

    public int getServId() {
        return servId;
    }

    public void setServId(int servId) {
        this.servId = servId;
    }

    public String getServTitle() {
        return servTitle;
    }

    public void setServTitle(String servTitle) {
        this.servTitle = servTitle;
    }

    public String getServSlug() {
        return servSlug;
    }

    public void setServSlug(String servSlug) {
        this.servSlug = servSlug;
    }

    public String getServContent() {
        return servContent;
    }

    public void setServContent(String servContent) {
        this.servContent = servContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceEntity that = (ServiceEntity) o;
        return servId == that.servId && Objects.equals(servTitle, that.servTitle) && Objects.equals(servSlug, that.servSlug) && Objects.equals(servContent, that.servContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(servId, servTitle, servSlug, servContent);
    }
}

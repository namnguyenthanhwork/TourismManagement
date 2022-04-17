package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Tour")
public class Tour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id")
    private Integer tourId;

    @Column(name = "tour_average_rating")
    private Integer tourAverageRating;

    @Column(name = "tour_is_active")
    private Boolean tourIsActive;

    @ManyToOne
    @JoinColumn(name = "tours_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category category;

    @OneToMany(mappedBy = "tour")
    private List<Bill> bills;

    @OneToMany(mappedBy = "tour")
    private List<Thumbnail> thumbnails;

    @OneToMany(mappedBy = "tour")
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "tour")
    private List<TourRating> tourRatings;

    @OneToMany (mappedBy = "tour")
    private List<TourTransport> tourTransports;

    @OneToMany(mappedBy = "tour")
    private List<TourDepartureDate> tourDepartureDates;

    @OneToMany(mappedBy = "tour")
    private List<TourServingObject> tourServingObjects;

    @OneToMany(mappedBy = "tour")
    private List<TourNote> tourNotes;

    @OneToMany(mappedBy = "tour")
    private List<TourService> tourServices;

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public Integer getTourAverageRating() {
        return tourAverageRating;
    }

    public void setTourAverageRating(Integer tourAverageRating) {
        this.tourAverageRating = tourAverageRating;
    }

    public Boolean getTourIsActive() {
        return tourIsActive;
    }

    public void setTourIsActive(Boolean tourIsActive) {
        this.tourIsActive = tourIsActive;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<TourRating> getTourRatings() {
        return tourRatings;
    }

    public void setTourRatings(List<TourRating> tourRatings) {
        this.tourRatings = tourRatings;
    }

    public List<TourTransport> getTourTransports() {
        return tourTransports;
    }

    public void setTourTransports(List<TourTransport> tourTransports) {
        this.tourTransports = tourTransports;
    }

    public List<TourDepartureDate> getTourDepartureDates() {
        return tourDepartureDates;
    }

    public void setTourDepartureDates(List<TourDepartureDate> tourDepartureDates) {
        this.tourDepartureDates = tourDepartureDates;
    }

    public List<TourServingObject> getTourServingObjects() {
        return tourServingObjects;
    }

    public void setTourServingObjects(List<TourServingObject> tourServingObjects) {
        this.tourServingObjects = tourServingObjects;
    }

    public List<TourNote> getTourNotes() {
        return tourNotes;
    }

    public void setTourNotes(List<TourNote> tourNotes) {
        this.tourNotes = tourNotes;
    }

    public List<TourService> getTourServices() {
        return tourServices;
    }

    public void setTourServices(List<TourService> tourServices) {
        this.tourServices = tourServices;
    }
}

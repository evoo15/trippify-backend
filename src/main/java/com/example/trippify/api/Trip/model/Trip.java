package com.example.trippify.api.Trip.model;

import com.example.trippify.api.User.model.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdDate;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Column(nullable = false)
    private String titre;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_id")
    @OnDelete(action = OnDeleteAction.CASCADE)

    private List<Trip_day> trip_days;


    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    private long daysNumber;


    private String imageUrl;

    public long getDaysNumber() {
        return daysNumber;
    }

    public void setDaysNumber(long daysNumber) {
        this.daysNumber = daysNumber;
    }

    @ManyToOne
    private User traveller;


    public Integer getNbComments() {
        return nbComments;
    }

    public void setNbComments(Integer nbComments) {
        this.nbComments = nbComments;
    }

    public Integer getNbViews() {
        return nbViews;
    }

    public void setNbViews(Integer nbViews) {
        this.nbViews = nbViews;
    }

    public Integer getNbLikes() {
        return nbLikes;
    }

    public void setNbLikes(Integer nbLikes) {
        this.nbLikes = nbLikes;
    }

    private Integer nbComments;
    private Integer nbViews;
    private Integer nbLikes;

    public List<Trip_day> getTrip_days() {
        return trip_days;
    }

    public void setTrip_days(List<Trip_day> trip_days) {
        this.trip_days = trip_days;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User getTraveller() {
        return traveller;
    }

    public void setTraveller(User traveller) {
        this.traveller = traveller;
    }
}


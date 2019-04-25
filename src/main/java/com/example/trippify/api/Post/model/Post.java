package com.example.trippify.api.Post.model;


import com.example.trippify.api.Trip.model.Trip;
import com.example.trippify.api.User.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @ManyToOne
    private User postMaker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Trip trip;

    private long day;

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    private String description;

    private String[] imageUrls;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String[] imageUrls) {
        this.imageUrls = imageUrls;
    }

    public User getPostMaker() {
        return postMaker;
    }

    public void setPostMaker(User postMaker) {
        this.postMaker = postMaker;
    }

    @JsonIgnore
    public Trip getTrip() {
        return trip;
    }

    @JsonIgnore
    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Long getTrip_id() {
        return trip.getId();
    }


}

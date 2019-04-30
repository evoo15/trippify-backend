package com.example.trippify.api.Trip.model;


import com.example.trippify.api.Post.model.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trip_days")
public class Trip_day {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer dayNumber;


    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonIgnore
    public Trip getTrip() {
        return trip;
    }

    @JsonIgnore
    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Post> addPost(Post post) {
        posts.add(post);
        return posts;
    }


    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Trip trip;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_day_id")
    private List<Post> posts;


}

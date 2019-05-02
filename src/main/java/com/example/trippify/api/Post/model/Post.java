package com.example.trippify.api.Post.model;


import com.example.trippify.api.Comment.model.Comment;
import com.example.trippify.api.Trip.model.Trip_day;
import com.example.trippify.api.User.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.loader.entity.BatchingEntityLoaderBuilder;

import javax.persistence.*;
import javax.xml.soap.Text;
import java.lang.reflect.Array;
import java.sql.Blob;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @ManyToOne
    private User postMaker;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "trip_day_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Trip_day trip_day;

    private long day;

    @JsonFormat(pattern = "hh:mm a")

    private LocalTime time;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @JoinColumn(name = "trip_id")
    private long trip_id;

    public long getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(long trip_id) {
        this.trip_id = trip_id;
    }

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public long getDay() {
        return day;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setDay(long day) {
        this.day = day;
    }

    @JsonIgnore
    public Trip_day getTrip_day() {
        return trip_day;
    }

    public void setTrip_day(Trip_day trip_day) {
        this.trip_day = trip_day;
    }

    private String description;
    private String place;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Column(name = "image_urls", columnDefinition = "longblob")
    private String[] imageUrls;

    public String[] getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String[] imageUrls) {
        this.imageUrls = imageUrls;
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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public User getPostMaker() {
        return postMaker;
    }

    public void setPostMaker(User postMaker) {
        this.postMaker = postMaker;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }


}

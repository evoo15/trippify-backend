package com.example.trippify.api.Post;

import com.example.trippify.api.Comment.model.Comment;
import com.example.trippify.api.Comment.service.CommentService;
import com.example.trippify.api.Post.model.Post;
import com.example.trippify.api.Post.service.PostService;
import com.example.trippify.api.Trip.model.Trip;
import com.example.trippify.api.Trip.model.Trip_day;
import com.example.trippify.api.Trip.service.TripService;
import com.example.trippify.api.Trip.service.repository.TripDayRepository;
import com.example.trippify.api.User.model.User;
import com.example.trippify.security.CurrentUser;
import com.example.trippify.security.UserPrincipal;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;


@RestController

public class PostResource {

    @Autowired
    private PostService postService;

    @Autowired
    private TripService tripService;

    @Autowired
    private TripDayRepository tripDayRepository;

    @Autowired
    private CommentService commentService;


    @PostMapping("/api/post")
    @PreAuthorize("hasRole('USER')")
    public Trip_day addPost(@CurrentUser UserPrincipal user, @RequestBody Post postRequest, @RequestParam long trip_day_id) {

        User postMaker = new User();
        postMaker.setId(user.getId());
        postRequest.setPostMaker(postMaker);
        Trip_day trip_Day = tripDayRepository.findById(trip_day_id).orElseThrow(() -> new EntityNotFoundException("trip day not found"));
        trip_Day.addPost(postRequest);
        // Trip trip = tripService.findById(post.getTrip_id()).get();
        // postRequest.setTrip(trip);

        return this.tripDayRepository.save(trip_Day);

    }

    @PutMapping("/api/post")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity editPost(@CurrentUser UserPrincipal user, @RequestBody Post postRequest) {

        Post post = postService.findById(postRequest.getId()).orElseThrow(() -> new EntityNotFoundException("post not found"));
        User postMaker = new User();
        postMaker.setId(user.getId());
        postRequest.setPostMaker(postMaker);

        Trip trip = tripService.findById(post.getTrip_id()).get();
        // postRequest.setTrip(trip);

        this.postService.save(postRequest);
        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);

    }

    @PostMapping("/api/post/comment")
    @PreAuthorize("hasRole('USER')")
    public Post addComment(@CurrentUser UserPrincipal user, @RequestBody Comment comment, @RequestParam long post_id) {

        Post post = postService.findById(post_id).orElseThrow(() -> new EntityNotFoundException("post not found"));
        comment.setPost(post);

        User commentor = new User();
        commentor.setId(user.getId());
        comment.setCommentor(commentor);
        post.addComment(comment);

        return postService.save(post);


    }
}


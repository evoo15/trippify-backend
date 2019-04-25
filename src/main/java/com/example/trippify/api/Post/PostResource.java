package com.example.trippify.api.Post;

import com.example.trippify.api.Post.model.Post;
import com.example.trippify.api.Post.service.PostService;
import com.example.trippify.api.Trip.model.Trip;
import com.example.trippify.api.Trip.service.TripService;
import com.example.trippify.api.User.model.User;
import com.example.trippify.security.CurrentUser;
import com.example.trippify.security.UserPrincipal;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;


@RestController

public class PostResource {

    @Autowired
    private PostService postService;
    @Autowired
    private TripService tripService;

    @PutMapping("/api/post")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity editPost(@CurrentUser UserPrincipal user, @RequestBody Post postRequest) {

        Post post = postService.findById(postRequest.getId()).orElseThrow(() -> new EntityNotFoundException("post not found"));
        User postMaker = new User();
        postMaker.setId(user.getId());
        postRequest.setPostMaker(postMaker);

        Trip trip = tripService.findById(post.getTrip_id()).get();
        postRequest.setTrip(trip);

        this.postService.save(postRequest);
        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);

    }
}


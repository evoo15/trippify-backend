package com.example.trippify.api.Trip.service;


import com.example.trippify.api.Post.model.Post;
import com.example.trippify.api.Post.service.PostService;
import com.example.trippify.api.Trip.model.Trip;
import com.example.trippify.api.Trip.service.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TripService implements ITripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    PostService postService;

    @Override

    public Trip create(Trip trip) {
        if (trip.getEndDate() != null && trip.getStartDate() != null) {
            trip.setDaysNumber(1 + ChronoUnit.DAYS.between(trip.getStartDate().toInstant(), trip.getEndDate().toInstant()));
        }
        trip.setNbComments(0);
        trip.setNbLikes(0);
        trip.setNbViews(0);

        List<Post> posts = new ArrayList<Post>();
        for (int i = 0; i < trip.getDaysNumber(); i++) {
            Post post = new Post();
            post.setTrip(trip);
            post.setDay(i + 1);
            posts.add(post);
        }

        postService.saveAll(posts);
        trip.setPosts(posts);

        return tripRepository.save(trip);
    }

    @Override
    public Trip save(Trip trip) {
        return tripRepository.save(trip);
    }

    @Override
    public void delete(Trip trip) {
        tripRepository.delete(trip);
    }

    @Override
    public List<Trip> findByTravellerId(Long id) {

        List<Trip> trips = tripRepository.findByTravellerId(id);

        for (Trip trip : trips) {
            trip.setNbViews(trip.getNbViews() + 1);
        }

        tripRepository.saveAll(trips);

        return trips;
    }

    @Override
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    @Override
    public Optional<Trip> findById(Long id) {
        Optional<Trip> trip = tripRepository.findById(id);
        trip.get().setNbViews(trip.get().getNbViews() + 1);
        tripRepository.save(trip.get());
        return trip;
    }
}

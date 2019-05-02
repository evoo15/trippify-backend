package com.example.trippify.api.Trip.service;


import com.example.trippify.api.Post.model.Post;
import com.example.trippify.api.Post.service.PostService;
import com.example.trippify.api.Trip.model.Trip;
import com.example.trippify.api.Trip.model.Trip_day;
import com.example.trippify.api.Trip.service.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
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
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        c.setTime(trip.getStartDate());

        List<Trip_day> trip_days = new ArrayList<Trip_day>();
        for (int i = 0; i < trip.getDaysNumber(); i++) {
            Trip_day trip_day = new Trip_day();

            trip_day.setTrip(trip);
            trip_day.setDayNumber(i + 1);
            trip_day.setDate(c.getTime());
            trip_days.add(trip_day);

            c.add(Calendar.DAY_OF_MONTH, 1);

        }

        // postService.saveAll(posts);
        trip.setTrip_days(trip_days);

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
    public Page<Trip> findAllPaged(Pageable page) {
        return tripRepository.findAll(page);
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

package com.example.trippify.api.Trip;


import com.example.trippify.api.Trip.model.Trip;
import com.example.trippify.api.Trip.repository.TripRepository;
import com.example.trippify.api.User.model.User;
import com.example.trippify.security.CurrentUser;
import com.example.trippify.security.UserPrincipal;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TripResource {

    @Autowired
    private TripRepository tripRepository;

    @JsonManagedReference
    @GetMapping("/api/trip")
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @GetMapping("/api/trip/me")
    @PreAuthorize("hasRole('USER')")
    public List<Trip> getAllTripsByUser(@CurrentUser UserPrincipal user) {

        return tripRepository.findByTravellerId(user.getId());
    }


}

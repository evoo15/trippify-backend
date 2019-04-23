package com.example.trippify.api.Trip;


import com.example.trippify.api.Trip.model.Trip;
import com.example.trippify.api.Trip.service.ITripService;
import com.example.trippify.api.Trip.service.TripService;
import com.example.trippify.api.User.model.User;
import com.example.trippify.security.CurrentUser;
import com.example.trippify.security.UserPrincipal;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
public class TripResource {

    @Autowired
    ITripService tripService;

    @JsonManagedReference
    @GetMapping("/api/trip")
    public List<Trip> getAllTrips() {
        return tripService.findAll();
    }

    @GetMapping("/api/trip/me")
    @PreAuthorize("hasRole('USER')")
    public List<Trip> getAllTripsByUser(@CurrentUser UserPrincipal user) {

        return tripService.findByTravellerId(user.getId());
    }

    @DeleteMapping("/api/trip")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Trip> deleteTripById(@CurrentUser UserPrincipal user, @RequestParam String id) {
        Trip trip = tripService.findById(Long.parseLong(id)).orElseThrow(() -> new EntityNotFoundException("trip not found"));
        if (user.getId().equals(trip.getTraveller().getId()))
            tripService.delete(trip);
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
        return new ResponseEntity(trip, HttpStatus.ACCEPTED);
    }

    @PostMapping("/api/trip")
    @PreAuthorize("hasRole('USER')")
    public List<Trip> addTrip(@CurrentUser UserPrincipal user, @RequestBody Trip trip) {

        User traveller = new User();
        traveller.setId(user.getId());
        trip.setTraveller(traveller);

        this.tripService.save(trip);
        return tripService.findByTravellerId(user.getId());
    }


}

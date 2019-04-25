package com.example.trippify.api.Trip.service;

import com.example.trippify.api.Trip.model.Trip;

import java.util.List;
import java.util.Optional;

public interface ITripService {

    Trip create(Trip trip);
    Trip save(Trip trip);

    void delete(Trip trip);

    List<Trip> findByTravellerId(Long id);

    List<Trip> findAll();

    Optional<Trip> findById(Long id);

}

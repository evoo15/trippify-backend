package com.example.trippify.api.Trip.service;

import com.example.trippify.api.Trip.model.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ITripService {

    Trip create(Trip trip);
    Trip save(Trip trip);

    void delete(Trip trip);

    List<Trip> findByTravellerId(Long id);

    List<Trip> findAll();

    Page<Trip> findAllPaged(Pageable page);

    Optional<Trip> findById(Long id);

}

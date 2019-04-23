package com.example.trippify.api.Trip.service;


import com.example.trippify.api.Trip.model.Trip;
import com.example.trippify.api.Trip.service.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TripService implements ITripService {

    @Autowired
    private TripRepository tripRepository;

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

        return tripRepository.findByTravellerId(id);
    }

    @Override
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    @Override
    public Optional<Trip> findById(Long id) {
        return tripRepository.findById(id);
    }
}

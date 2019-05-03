package com.example.trippify.api.Trip.service.repository;

import com.example.trippify.api.Trip.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByTravellerId(long userId);

    List<Trip> findAllByTitreIgnoreCaseContaining(String keyword);


}

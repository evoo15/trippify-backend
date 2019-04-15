package com.example.trippify.api.Trip.repository;

import com.example.trippify.api.Trip.model.Trip;
import com.example.trippify.api.User.model.User;
import com.example.trippify.security.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByTravellerId(long userId);


}

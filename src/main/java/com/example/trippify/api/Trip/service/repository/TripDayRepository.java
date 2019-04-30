package com.example.trippify.api.Trip.service.repository;


import com.example.trippify.api.Trip.model.Trip_day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripDayRepository extends JpaRepository<Trip_day, Long> {


}

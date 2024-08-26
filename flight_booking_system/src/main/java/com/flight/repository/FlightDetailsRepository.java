package com.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flight.model.FlightDetails;

import java.util.List;

@Repository
public interface FlightDetailsRepository extends JpaRepository<FlightDetails, Long> {
    List<FlightDetails> findBySourceAndDestinationAndDate(String source, String destination, String date);
    
    @Query("SELECT f FROM FlightDetails f WHERE " +
            "(:source IS NULL OR f.source = :source) AND " +
            "(:destination IS NULL OR f.destination = :destination) AND " +
            "(:date IS NULL OR f.date = :date)")
    List<FlightDetails> findFlights(@Param("source") String source, 
            @Param("destination") String destination, 
            @Param("date") String date);
}
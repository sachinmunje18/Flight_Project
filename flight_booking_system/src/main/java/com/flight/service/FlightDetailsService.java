package com.flight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.model.FlightDetails;
import com.flight.repository.FlightDetailsRepository;

import java.util.List;

@Service
public class FlightDetailsService {

    @Autowired
    private FlightDetailsRepository flightRepository;

    public void saveFlight(FlightDetails flight) {
        flightRepository.save(flight);
    }

    public List<FlightDetails> searchFlights(String source, String destination, String date) {
        if (source != null && destination != null && date != null) {
            return flightRepository.findBySourceAndDestinationAndDate(source, destination, date);
        } else {
            // If any parameter is null, handle accordingly, e.g., find all flights matching non-null criteria
            return flightRepository.findFlights(source, destination, date);
        }
    }
}
package com.flight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.model.FlightDetails;
import com.flight.repository.FlightDetailsRepository;

import java.util.List;

@Service // Marks this class as a Spring service component, making it eligible for component scanning and dependency injection
public class FlightDetailsService {

    @Autowired // Injects the FlightDetailsRepository bean into this class
    private FlightDetailsRepository flightRepository;

    // Save a new flight or update an existing flight
    // This method uses the repository's save method to persist the flight details
    public void saveFlight(FlightDetails flight) {
        flightRepository.save(flight);
    }

    // Search for flights based on source, destination, and date
    // If all parameters are provided, it uses a specific method to find flights
    // If any parameter is null, it uses a more flexible search method to find flights based on available criteria
    public List<FlightDetails> searchFlights(String source, String destination, String date) {
        if (source != null && destination != null && date != null) {
            return flightRepository.findBySourceAndDestinationAndDate(source, destination, date);
        } else {
            // If any parameter is null, find flights using a more flexible search method
            return flightRepository.findFlights(source, destination, date);
        }
    }
}

package com.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flight.model.FlightDetails;
import com.flight.service.FlightDetailsService;

import java.util.List;

@RestController // Marks this class as a RESTful controller
@RequestMapping("/api/flights") // Base URL for all endpoints in this controller
public class FlightDetailsController {

	@Autowired
	private FlightDetailsService flightService; // Injects the FlightDetailsService

	@PostMapping("/add") // Handles HTTP POST requests to add a new flight
	public ResponseEntity<String> addFlight(@RequestBody FlightDetails flight) {
		flightService.saveFlight(flight); // Saves the flight details
		return ResponseEntity.ok("Flight details added successfully"); // Returns a success message
	}

	@GetMapping("/search") // Handles HTTP GET requests to search for flights
	public List<FlightDetails> searchFlights(@RequestParam(required = false) String source, // Source airport (optional)
			@RequestParam(required = false) String destination, // Destination airport (optional)
			@RequestParam(required = false) String date) { // Flight date (optional)
		return flightService.searchFlights(source, destination, date); // Returns a list of matching flights
	}
}

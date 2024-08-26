package com.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flight.model.FlightDetails;
import com.flight.service.FlightDetailsService;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightDetailsController {

    @Autowired
    private FlightDetailsService flightService;

    @PostMapping("/add")
    public ResponseEntity<String> addFlight(@RequestBody FlightDetails flight) {
        flightService.saveFlight(flight);
        return ResponseEntity.ok("Flight details added successfully");
    }

    @GetMapping("/search")
    public List<FlightDetails> searchFlights(
            @RequestParam(required = false) String source,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) String date) {
        return flightService.searchFlights(source, destination, date);
    }
}
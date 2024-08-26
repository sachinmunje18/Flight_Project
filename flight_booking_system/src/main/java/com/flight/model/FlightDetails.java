package com.flight.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Marks this class as a JPA entity
public class FlightDetails {

	@Id // Primary key for the entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID
	private Long id;

	private String source; // Departure location
	private String destination; // Arrival location
	private String date; // Flight date
	private String airline; // Airline name
	private String flightNumber; // Unique flight number
	private String departureTime; // Departure time
	private String arrivalTime; // Arrival time
	private double cost; // Cost of the flight

	// Default constructor
	public FlightDetails() {
		super();
	}

	// Parameterized constructor
	public FlightDetails(Long id, String source, String destination, String date, String airline, String flightNumber,
			String departureTime, String arrivalTime, double cost) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.date = date;
		this.airline = airline;
		this.flightNumber = flightNumber;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.cost = cost;
	}

	// Getters and setters for all fields
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}

package com.flight.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Marks this class as a JPA entity
public class SearchQuery {

	@Id // Primary key for the entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID
	private Long id;

	private String source; // Departure location for the search query
	private String destination; // Arrival location for the search query
	private String date; // Date of the flight for the search query

	// Default constructor
	public SearchQuery() {
	}

	// Parameterized constructor
	public SearchQuery(Long id, String source, String destination, String date) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.date = date;
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
}

package com.flight.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters, toString, equals, and hashCode methods
@Entity // Marks this class as a JPA entity
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all arguments
@Builder // Provides a builder pattern for creating instances
public class User {

	@Id // Primary key for the entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID
	private Integer id; // Unique identifier for the user

	private String name; // Name of the user
	private String email; // Email address of the user
	private String password; // Password for user authentication

}

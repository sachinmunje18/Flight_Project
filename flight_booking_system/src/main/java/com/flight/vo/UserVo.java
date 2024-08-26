package com.flight.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Generates a no-arguments constructor
@AllArgsConstructor // Generates a constructor with all arguments
@Builder // Provides a builder pattern for creating instances of this class
public class UserVo {

    private Integer id; // Unique identifier for the user
    private String name; // Name of the user
    private String email; // Email address of the user
    private String password; // Password for the user

}

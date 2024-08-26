package com.flight.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.model.User;
import com.flight.service.UserService;
import com.flight.vo.UserVo;

import lombok.RequiredArgsConstructor;

@RestController // Indicates that this class is a RESTful controller
@RequiredArgsConstructor // Automatically generates a constructor for final fields
@RequestMapping("/flight") // Base URL for all endpoints in this controller
public class UserController {

	private final UserService userService; // Service to handle user-related operations

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveUser(@RequestBody UserVo uservo) {
		// Check if the email is already registered
		if (userService.findUserByEmail(uservo.getEmail()) != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message\": \"Email is already registered!\"}"); // Return
																														// conflict
																														// status
																														// if
																														// email
																														// exists
		}

		// Build the user object and register it
		User user = User.builder().name(uservo.getName()).email(uservo.getEmail()).password(uservo.getPassword())
				.build();
		userService.registerMember(user); // Register the new user

		return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"User registered successfully!\"}"); // Return
																													// success
																													// status
																													// after
																													// registration
	}

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> loginUser(@RequestBody UserVo uservo) {
		// Build the user object based on the provided email and password
		User user = User.builder().email(uservo.getEmail()).password(uservo.getPassword()).build();

		// Check if the user with the provided email and password exists
		User getUser = userService.EmailAndPassword(user.getEmail(), user.getPassword());

		// Respond with success or failure based on authentication result
		if (getUser != null) {
			return ResponseEntity.ok("{\"message\": \"LOGIN SUCCESSFULLY!\"}"); // Return success message if login is
																				// valid
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"INVALID login\"}"); // Return
																											// unauthorized
																											// status if
																											// login is
																											// invalid
		}
	}
}

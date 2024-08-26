package com.flight.service;

import org.springframework.stereotype.Service;

import com.flight.model.User;
import com.flight.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService  {
	
	private final UserRepository userRepository;
	
	// Save register user
	public User registerMember(User user) {
		return userRepository.save(user);
	}

	// Check whether user is valid or not by checking from database 
	public User EmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}
}

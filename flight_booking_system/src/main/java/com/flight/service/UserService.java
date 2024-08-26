package com.flight.service;

import com.flight.model.User;
import com.flight.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // Marks this class as a Spring service component, making it eligible for component scanning and dependency injection
@RequiredArgsConstructor // Automatically generates a constructor with required arguments (i.e., final fields)
public class UserService {

    private final UserRepository userRepository; // Injected repository instance

    // Registers a new user by saving the user entity to the database
    public void registerMember(User user) {
        userRepository.save(user);
    }

    // Finds a user by their email
    // This method is used to check if a user with the given email already exists
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Finds a user by their email and password
    // This method is used for authentication to validate user credentials
    public User EmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}

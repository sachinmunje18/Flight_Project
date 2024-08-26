package com.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.flight.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by their email address
    // This method will generate a query to search for a user with the specified email
    User findByEmail(String email);

    // Find a user by their email address and password
    // This method will generate a query to search for a user with the specified email and password
    User findByEmailAndPassword(String email, String password);
}

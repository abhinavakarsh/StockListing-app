package com.example.userprofileservice.repository;

import com.example.userprofileservice.model.User; // Import the User model
import org.springframework.data.jpa.repository.JpaRepository; // Import JpaRepository for CRUD operations
import org.springframework.stereotype.Repository; // Import Repository annotation

import java.util.Optional; // Import Optional for handling null values

@Repository // Marks this interface as a Spring Data repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Method to find a user by username
    Optional<User> findByUsername(String username);

    // Method to find a user by email
    Optional<User> findByEmail(String email);
}
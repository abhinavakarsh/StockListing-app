package com.example.AuthenticationService.service;

import com.example.AuthenticationService.model.User;
import com.example.AuthenticationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        validateUsername(user.getUsername());
        User newUser = new User(user.getUsername(), user.getPassword());
        return userRepository.save(newUser);
    }

    private void validateUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException("Username already exists: " + username);
        }
    }

    public void validateUserCredentials(User user, String password) throws InvalidCredentialsException {
        if (user == null || !user.getPassword().equals(password)) {
            throw new InvalidCredentialsException("Invalid username or password.");
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username); // Return null if not found
    }

    // Custom exceptions
    static class UsernameAlreadyExistsException extends RuntimeException {
        public UsernameAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class InvalidCredentialsException extends RuntimeException {
        public InvalidCredentialsException(String message) {
            super(message);
        }
    }
}

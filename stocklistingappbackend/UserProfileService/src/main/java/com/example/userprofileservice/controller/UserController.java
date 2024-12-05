package com.example.userprofileservice.controller;

import com.example.userprofileservice.model.User;
import com.example.userprofileservice.service.KafkaProducer;
import com.example.userprofileservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")  // Allow CORS for this controller
public class UserController {

    private final UserService userService;
    private final KafkaTemplate<String, User> kafkaTemplate;

    @Autowired
    public UserController(UserService userService, KafkaTemplate<String, User> kafkaTemplate) {
        this.userService = userService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Registered Successfully"),
            @ApiResponse(responseCode = "400", description = "Username or email already exists")
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        try {
            // Register the user
            User savedUser = userService.registerUser(user);

            // Send user data to Kafka (for further processing or messaging)
            kafkaTemplate.send("User-Data", user);

            // Return success response with the saved user object
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            // If any error occurs, return a bad request with the error message
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}

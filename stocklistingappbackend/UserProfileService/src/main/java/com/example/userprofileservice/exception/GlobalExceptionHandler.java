package com.example.userprofileservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // Allows the class to handle exceptions globally across all controllers
public class GlobalExceptionHandler {

    // Method to handle validation exceptions globally
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Sets the HTTP status to 400 Bad Request
    @ExceptionHandler(MethodArgumentNotValidException.class) // Specifies the exception type to handle
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        System.out.println("Global error"); // Log to console when an error occurs

        Map<String, String> errors = new HashMap<>(); // Create a map to hold field errors

        // Loop through all errors and populate the map with field names and error messages
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField(); // Get the field name that caused the error
            String errorMessage = error.getDefaultMessage(); // Get the default error message
            errors.put(fieldName, errorMessage); // Add field name and error message to the map
        });

        // Return the map of errors with a 400 Bad Request status
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
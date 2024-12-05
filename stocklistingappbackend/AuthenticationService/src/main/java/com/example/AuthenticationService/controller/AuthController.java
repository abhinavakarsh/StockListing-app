package com.example.AuthenticationService.controller;

import com.example.AuthenticationService.model.User;
import com.example.AuthenticationService.service.AuthenticationService;
import com.example.AuthenticationService.service.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationService authenticationService, JwtUtil jwtUtil) {
        this.authenticationService = authenticationService;
        this.jwtUtil = jwtUtil;
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody User user) {
//        authenticationService.saveUser(user);
//        return ResponseEntity.ok("User registered successfully.");
//    }

    @Operation(summary = "Login user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid username or password")
    })
    @PostMapping("/api/users/authenticate")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody User loginDetails) {
        try {
            // Fetch user by username
            User user = authenticationService.findByUsername(loginDetails.getUsername());

            // Validate if user exists
            if (user == null) {
                return ResponseEntity.status(404).body(new AuthResponse("User not found."));
            }

            // Validate the user's credentials
            authenticationService.validateUserCredentials(user, loginDetails.getPassword());

            // Generate JWT token with username and user ID
            String token = jwtUtil.generateToken(user.getUsername(), user.getId());

            // Return the token in the response
            return ResponseEntity.ok(new AuthResponse(token));

        } catch (AuthenticationService.InvalidCredentialsException e) {
            // Return error response if credentials are invalid
            return ResponseEntity.status(401).body(new AuthResponse("Invalid Credentials."));

        } catch (Exception e) {
            // Handle other unforeseen errors
            return ResponseEntity.status(500).body(new AuthResponse("An error occurred while authenticating."));
        }
    }



    @Operation(summary = "Validate JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token is valid"),
            @ApiResponse(responseCode = "401", description = "Token is invalid or expired"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping("/protected")
    public ResponseEntity<String> validateToken(@RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(403).body("Forbidden"); // Return 403 Forbidden
        }

        String jwtToken = token.substring(7); // Remove "Bearer " prefix
        if (jwtUtil.isValidToken(jwtToken)) {
            return ResponseEntity.ok("Token is valid");
        }
        return ResponseEntity.status(401).body("Token is invalid or expired");
    }

    // Create a simple DTO to return the token
    public static class AuthResponse {
        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}

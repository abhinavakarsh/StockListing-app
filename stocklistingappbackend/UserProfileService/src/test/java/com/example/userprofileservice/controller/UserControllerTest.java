//package com.example.userprofileservice.controller;
//
//import com.example.userprofileservice.model.User; // Import the User model
//import com.example.userprofileservice.service.UserService; // Import the UserService interface
//import org.junit.jupiter.api.BeforeEach; // Import BeforeEach for setup
//import org.junit.jupiter.api.Test; // Import Test for test methods
//import org.mockito.ArgumentMatchers; // Import ArgumentMatchers for matching arguments
//import org.mockito.InjectMocks; // Import InjectMocks for injecting mocks
//import org.mockito.Mock; // Import Mock for creating mock objects
//import org.mockito.MockitoAnnotations; // Import MockitoAnnotations for initializing mocks
//import org.springframework.http.ResponseEntity; // Import ResponseEntity for response handling
//import static org.junit.jupiter.api.Assertions.*; // Import assertion methods
//import static org.mockito.Mockito.*; // Import Mockito static methods
//
//class UserControllerTest {
//
//    @Mock // Mock the UserService
//    private UserService userService;
//
//    @InjectMocks // Inject the mocked UserService into UserController
//    private UserController userController;
//
//    @BeforeEach // Setup method to initialize mocks before each test
//    void setUp() {
//        MockitoAnnotations.openMocks(this); // Initialize the mocks
//    }
//
//    @Test // Test for successful user registration
//    void testRegisterUser_Success() {
//        User user = new User();
//        user.setUsername("testuser");
//        user.setEmail("testuser@example.com");
//        user.setPassword("password");
//        user.setFirstName("Firstname");
//        user.setLastName("Lastname");
//
//        // Mock the userService to return the saved user
//        when(userService.registerUser(user)).thenReturn(user);
//
//        // Act
//        ResponseEntity<?> response = userController.registerUser(user);
//
//        // Assert
//        assertEquals(200, response.getStatusCodeValue()); // Check that the response status is 200
//        assertEquals(user, response.getBody()); // Validate the response body
//        verify(userService, times(1)).registerUser(user); // Verify that registerUser was called once
//    }
//
//    @Test // Test for user registration with an existing username or email
//    void testRegisterUser_UsernameExists() {
//        User user = new User();
//        user.setUsername("existingUser");
//        user.setEmail("existing@example.com");
//        user.setPassword("password");
//        user.setFirstName("firstname");
//        user.setLastName("Latname");
//        // Mock the userService to throw UsernameExistsException
//        when(userService.registerUser(user)).thenThrow(new UserService.UsernameExistsException("Username already exists"));
//
//        // Act
//        ResponseEntity<?> response = userController.registerUser(user);
//
//        // Assert
//        assertEquals(400, response.getStatusCodeValue()); // Check that the response status is 400
//        assertEquals("Username already exists", response.getBody()); // Validate the response body
//        verify(userService, times(1)).registerUser(user); // Verify that registerUser was called once
//    }
//
//    @Test // Test for user registration with an existing email
//    void testRegisterUser_EmailExists() {
//        User user = new User();
//        user.setUsername("newUser");
//        user.setEmail("existing@example.com");
//        user.setPassword("password");
//        user.setFirstName("firstname");
//        user.setLastName("Latname");
//        // Mock the userService to throw EmailExistsException
//        when(userService.registerUser(user)).thenThrow(new UserService.EmailExistsException("Email already exists"));
//
//        // Act
//        ResponseEntity<?> response = userController.registerUser(user);
//
//        // Assert
//        assertEquals(400, response.getStatusCodeValue()); // Check that the response status is 400
//        assertEquals("Email already exists", response.getBody()); // Validate the response body
//        verify(userService, times(1)).registerUser(user); // Verify that registerUser was called once
//    }
//
//
//}
//package com.example.userprofileservice.service;
//
//import com.example.userprofileservice.model.User; // Import the User model
//import com.example.userprofileservice.repository.UserRepository; // Import the UserRepository interface
//import org.junit.jupiter.api.BeforeEach; // Import BeforeEach annotation for setup
//import org.junit.jupiter.api.Test; // Import Test annotation for test methods
//import org.mockito.ArgumentMatchers; // Import ArgumentMatchers for matching arguments
//import org.mockito.InjectMocks; // Import InjectMocks for injecting mocks
//import org.mockito.Mock; // Import Mock for creating mock objects
//import org.mockito.MockitoAnnotations; // Import MockitoAnnotations for initializing mocks
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*; // Import assertion methods
//import static org.mockito.Mockito.*; // Import Mockito static methods
//
//class UserServiceTest {
//
//    @Mock // Mock the UserRepository
//    private UserRepository userRepository;
//
//    @InjectMocks // Inject the mocked UserRepository into UserService
//    private UserService userService;
//
//    @BeforeEach // Setup method to initialize mocks before each test
//    void setUp() {
//        MockitoAnnotations.openMocks(this); // Initialize the mocks
//    }
//
//
//    @Test // Test for username already exists
//    void testRegisterUser_UsernameExists() {
//        User user = new User();
//        user.setUsername("existingUser");
//        user.setEmail("existing@example.com");
//        user.setPassword("password");
//
//        // Mock the repository to simulate existing username
//        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
//
//        // Act & Assert
//        UserService.UsernameExistsException exception = assertThrows(UserService.UsernameExistsException.class, () -> {
//            userService.registerUser(user); // Attempt to register user
//        });
//
//        assertEquals("Username already exists", exception.getMessage()); // Check exception message
//        verify(userRepository, never()).save(any()); // Verify save was never called
//    }
//
//    @Test // Test for email already exists
//    void testRegisterUser_EmailExists() {
//        User user = new User();
//        user.setUsername("newUser");
//        user.setEmail("existing@example.com");
//        user.setPassword("password");
//
//        // Mock the repository to simulate existing email
//        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
//        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
//
//        // Act & Assert
//        UserService.EmailExistsException exception = assertThrows(UserService.EmailExistsException.class, () -> {
//            userService.registerUser(user); // Attempt to register user
//        });
//
//        assertEquals("Email already exists", exception.getMessage()); // Check exception message
//        verify(userRepository, never()).save(any()); // Verify save was never called
//    }
//
//    @Test // Test finding a user by username
//    void testFindByUsername() {
//        User user = new User();
//        user.setUsername("testuser");
//        user.setEmail("testuser@example.com");
//        user.setPassword("password");
//
//        // Mock the repository to return the user when searching by username
//        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
//
//        // Act
//        Optional<User> foundUser = userService.findByUsername("testuser");
//
//        // Assert
//        assertTrue(foundUser.isPresent()); // Check that user is found
//        assertEquals(user.getUsername(), foundUser.get().getUsername()); // Validate username
//    }
//}
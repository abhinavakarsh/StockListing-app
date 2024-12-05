package com.example.userprofileservice.model;
import jakarta.persistence.*; // Import JPA annotations
import jakarta.validation.constraints.Email;

@Entity // Marks this class as a JPA entity
@Table(name = "users") // Specifies the table name in the database
public class User {
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
    @Id // Indicates the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID value
    private Long id; // Unique identifier for the user
    @Column(unique = true, nullable = false) // Specifies the column properties in the database
    private String username; // Unique username for the user
    @Column(unique = true, nullable = false) // Specifies the column properties in the database
    @Email(message="Invalid Email",regexp="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}")
    private String email; // Unique email address for the user
    @Column(nullable = false) // Specifies the column properties in the database
     // Password for user authentication
    // Specifies the column properties for first name

    private String password;

    @Column(nullable = false)
    private String firstName; // First name of the user
    @Column(nullable = false) // Specifies the column properties for last name
    private String lastName; // Last name of the user
    // Getter for id

    public Long getId() {
        return id;
    }
    // Setter for id
    public void setId(Long id) {
        this.id = id;
    }
    // Getter for username
    public String getUsername() {
        return username;
    }
    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }
    // Getter for email
    public String getEmail() {
        return email;
    }
    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }
    // Getter for password
    public String getPassword() {
        return password;
    }
    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }
    // Getter for first name
    public String getFirstName() {
        return firstName;
    }
    // Setter for first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    // Getter for last name
    public String getLastName() {
        return lastName;
    }
    // Setter for last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
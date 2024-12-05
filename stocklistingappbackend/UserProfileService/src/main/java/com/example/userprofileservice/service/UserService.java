package com.example.userprofileservice.service;

import com.example.userprofileservice.model.User;
import com.example.userprofileservice.model.UserDTO;
import com.example.userprofileservice.repository.UserRepository;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private static final String TOPIC = "User-Data";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Producer<String, UserDTO> producer;

    public UserService(Producer<String, UserDTO> producer) {
        this.producer = producer;
    }

    public void send(String topic, String key, UserDTO userDTO) {
        ProducerRecord<String, UserDTO> record = new ProducerRecord<>(topic, key, userDTO);
        producer.send(record);
    }

    @Autowired
    public UserService(UserRepository userRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate; // Inject Kafka template
    }

    public static class UsernameExistsException extends RuntimeException {
        public UsernameExistsException(String message) {
            super(message);
        }
    }

    public static class EmailExistsException extends RuntimeException {
        public EmailExistsException(String message) {
            super(message);
        }
    }

    public User registerUser(User user) throws UsernameExistsException, EmailExistsException {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameExistsException("Username already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailExistsException("Email already exists");
        }
        User savedUser = userRepository.save(user);

        // Send user registration message to Kafka
        String message = String.format("{\"username\": \"%s\", \"email\": \"%s\"}", user.getUsername(), user.getEmail());
        kafkaTemplate.send(TOPIC, savedUser.getUsername(), message); // Use the Kafka template to send the message

        return savedUser;
    }
    public void registerUser(String userData) {
        kafkaTemplate.send(TOPIC, userData);
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
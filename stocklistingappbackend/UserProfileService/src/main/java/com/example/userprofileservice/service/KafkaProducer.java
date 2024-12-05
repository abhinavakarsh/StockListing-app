package com.example.userprofileservice.service;

import com.example.userprofileservice.model.UserDTO;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
public class KafkaProducer {

    @Autowired
    UserService userService;
    public void sendRegistrationMessage(UserDTO userDTO) {
        userService.send("User-Data", userDTO.getUsername(), userDTO);
        System.out.println("Sent registration message: " + userDTO);
    }
    public void sendLoginMessage(UserDTO userDTO) {
        userService.send("User-Data",userDTO.getUsername(),userDTO);
        System.out.println("user registration"+userDTO);// Replace "login-topic" with your topic name
    }
}
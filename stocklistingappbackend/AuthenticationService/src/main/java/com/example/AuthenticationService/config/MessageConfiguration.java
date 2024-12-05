//package com.example.AuthenticationService.config;
//
//import com.example.AuthenticationService.model.UserDTO;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.TopicBuilder;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class MessageConfiguration {
//    @Bean
//    public ProducerFactory<String,UserDTO> producerFactory(){
//        Map<String,Object> config=new HashMap<>();
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"kafka:9092");
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
//        return new DefaultKafkaProducerFactory<>(config);
//    }
//    @Bean
//    public KafkaTemplate<String, UserDTO> kafkaTemplate(){return new KafkaTemplate<>(producerFactory());}
//
//    @Bean
//    public NewTopic tasktopic(){
//        return TopicBuilder.name("User-Data").partitions(1).replicas(1).build();
//    }
//}

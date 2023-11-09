package com.example.demo.controller;

import com.example.demo.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/kafka")
public class kafkaController {
    private KafkaProducer kafkaProducer;

    public kafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/publish")
    public ResponseEntity<?> publish (@RequestParam String message){
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent!!");
    }
}

package com.example.demo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Service
public class KafkaProducer {
    @Value("${spring.kafka.topic.name}")
    private String topicName;
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        //LOGGER.info(String.format("%s message sent %s",topicName, message));
        kafkaTemplate.send(topicName, message).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                LOGGER.info("Fail");
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOGGER.info(String.format("%s message sent %s",topicName, message));
            }
        });
    }
}

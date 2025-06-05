package com.pkonopacki1.weather_monitor_app_consumer.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Component
@Log
public class KafkaConsumer {

    @KafkaListener(id = "weatherConsumerGroup", topics = "weather.report")
    public void listen(String weatherObject) {
        log.info("Received: " + weatherObject);
    }

}

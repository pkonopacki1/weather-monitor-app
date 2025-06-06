package com.pkonopacki1.weather_monitor_app_consumer.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.pkonopacki1.common.model.WeatherResponse;

import lombok.extern.java.Log;

@Component
@Log
public class KafkaConsumer {

    @KafkaListener(id = "weatherConsumerGroup", topics = "weather.report")
    public void listen(WeatherResponse weatherObject) {
        log.info("Received: " + weatherObject);
    }

}

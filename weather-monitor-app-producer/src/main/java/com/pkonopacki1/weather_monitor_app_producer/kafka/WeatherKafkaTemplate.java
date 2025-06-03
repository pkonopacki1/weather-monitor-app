package com.pkonopacki1.weather_monitor_app_producer.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pkonopacki1.weather_monitor_app_producer.rest.WeatherApiRestClient;
import com.pkonopacki1.weather_monitor_app_producer.rest.model.WeatherResponse;

import lombok.extern.java.Log;

@EnableScheduling
@Log
@Component
public class WeatherKafkaTemplate {

    @Autowired
    private KafkaTemplate<String, WeatherResponse> template;

    @Autowired
    private WeatherApiRestClient restClient;

    @Scheduled(fixedRate = 1000)
    public void publish() {
        var weatherReport = restClient.getCityWeather("Poznan");

        weatherReport.ifPresent((body) -> {
            template.send("weather.report", body);
            log.info("Message sent successfully");
        });
    }

}

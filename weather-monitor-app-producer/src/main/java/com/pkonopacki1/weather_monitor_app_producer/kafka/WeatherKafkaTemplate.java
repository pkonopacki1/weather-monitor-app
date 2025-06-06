package com.pkonopacki1.weather_monitor_app_producer.kafka;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pkonopacki1.common.model.WeatherResponse;
import com.pkonopacki1.weather_monitor_app_producer.rest.WeatherApiRestClient;

import lombok.extern.java.Log;

@EnableScheduling
@Log
@Component
public class WeatherKafkaTemplate {

  @Value("${weather.api.cities}")
  private List<String> cities;

  @Autowired
  private KafkaTemplate<String, WeatherResponse> template;

  @Autowired
  private WeatherApiRestClient restClient;

  @Scheduled(fixedRateString = "${app.fixed-rate}")
  public void publish() {
    String city = cities.get(new Random().nextInt(cities.size()));
    var weatherReport = restClient.getCityWeather(city);

    weatherReport.ifPresent((body) -> {
      template.send("weather.report", city, body);
      log.info(String.format("Message regarding %s was sent", city));
    });
  }

}

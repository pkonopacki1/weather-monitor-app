package com.pkonopacki1.weather_monitor_app_consumer.kafka;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.pkonopacki1.common.model.WeatherResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaConsumer {

  static final Map<String, Double> cityPrecipitation = new ConcurrentHashMap<>();

  @KafkaListener(id = "weatherConsumerGroup", topics = "weather.report")
  public void listen(ConsumerRecord<String, WeatherResponse> record) {
    double currentPrecip = record.value().getCurrent().getPrecip_mm();
    String city = record.key();
    log.info("Received record for {}", city);
    if (cityPrecipitation.containsKey(city)) {
      double storedPrecip = cityPrecipitation.get(city);
      if (storedPrecip != currentPrecip) {
        if (currentPrecip > storedPrecip) {
          log.info("Looks like it's going to rain in {}, current precipitation is: {}", city, currentPrecip);
        } else {
          log.info("The rain is going away in {}, current precipitation is: {}", city, currentPrecip);
        }
      } else {
        log.info("There is no change in precipitation level in {}, currently it's {} mm", city, currentPrecip);
      }
    }
    cityPrecipitation.put(city, currentPrecip);
  }

}

package org.konopackipio1.messaging;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.konopackipio1.rest.WeatherApiClient;
import org.konopackipio1.rest.WeatherResponse;
import org.slf4j.Logger;

import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WeatherProducer {

  Logger logger = org.slf4j.LoggerFactory.getLogger(WeatherProducer.class);

  @ConfigProperty(name = "weather-api.cities")
  List<String> cities;

  @ConfigProperty(name = "app.produce-period.seconds")
  int producePeriodSeconds;

  @RestClient
  WeatherApiClient weatherApiClient;

  @Outgoing("weather-raw")
  public Multi<WeatherResponse> produceWeatherUpdates() {
    return Multi.createFrom().ticks().every(Duration.ofSeconds(producePeriodSeconds))
        .map(tick -> {
          String city = cities.get(new Random().nextInt(cities.size()));
          WeatherResponse newWeatherResponse = weatherApiClient.getWeather(city);
          logger.info("Received information for {}", newWeatherResponse.location().name());
          return newWeatherResponse;
        });
  }

}

package org.konopackipio1.messaging;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.konopackipio1.rest.WeatherResponse;
import org.slf4j.Logger;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WeatherConsumer {

  Logger logger = org.slf4j.LoggerFactory.getLogger(WeatherConsumer.class);

  @Incoming("weather-raw")
  public Uni<Void> consumeWeatherUpdates(WeatherResponse weatherResponse) {
    logger.info("Consumed weather information for: {}", weatherResponse.location().name());
    logger.info("Temperature: {}°C, Description: {}",
        weatherResponse.current().temp_c(),
        weatherResponse.current().condition().text());
    return Uni.createFrom().voidItem();
  }

}

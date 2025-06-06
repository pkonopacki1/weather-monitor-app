package com.pkonopacki1.weather_monitor_app_producer.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.pkonopacki1.common.model.WeatherResponse;

@Component
public class WeatherApiRestClient {

        @Value("${weather.api.key}")
        private String apiKey;

        private static final String baseUrl = "https://api.weatherapi.com/v1";

        public Optional<WeatherResponse> getCityWeather(String city) {
                var restClient = RestClient.builder()
                                .baseUrl(baseUrl)
                                .build();

                var responseBody = restClient.get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/current.json")
                                                .queryParam("key", apiKey)
                                                .queryParam("q", city)
                                                .build())
                                .retrieve()
                                .body(WeatherResponse.class);

                return responseBody != null ? Optional.of(responseBody) : Optional.empty();
        }

}

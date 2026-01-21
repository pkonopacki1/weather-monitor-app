package org.konopackipio1.rest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.konopackipio1.rest.model.WeatherResponse;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class WeatherApiClientTest {

  @InjectMock
  @RestClient
  WeatherApiClient mock;

  @BeforeEach
  public void setUp() throws StreamReadException, DatabindException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    WeatherResponse weatherResponse = objectMapper.readValue(
        getClass().getClassLoader().getResourceAsStream("getCurrentResponseExample.json"),
        WeatherResponse.class);
    when(mock.getWeather(anyString())).thenReturn(weatherResponse);
  }

  @Test
  void testGetWeather() {

  }
}

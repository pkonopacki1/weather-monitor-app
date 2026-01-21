package org.konopackipio1.rest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
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

  ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void testGetWeather() throws StreamReadException, DatabindException, IOException {
    // Arrange
    when(mock.getWeather(anyString())).thenReturn(readWeatherResponseFromFile("getCurrentResponseExample.json"));

    // Act
    var response = mock.getWeather("Szczecin");

    // Assert
    Assertions.assertEquals(response.location().name(), "Szczecin");
  }

  private WeatherResponse readWeatherResponseFromFile(String filename)
      throws StreamReadException, DatabindException, IOException {
    return objectMapper.readValue(getClass().getClassLoader().getResourceAsStream(filename), WeatherResponse.class);
  }
}

package org.konopackipio1.rest;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.rest.client.reactive.ClientQueryParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/current.json")
@RegisterRestClient
@ClientQueryParam(name = "key", value = "${weather-api.key}")
public interface WeatherApiClient {

  @GET
  WeatherResponse getWeather(@QueryParam("q") String city);

}

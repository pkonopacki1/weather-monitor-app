package com.pkonopacki1.weather_monitor_app_consumer.rest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WeatherResponse {
    private Location location;
    private Current current;

}

package com.pkonopacki1.weather_monitor_app_consumer.rest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private String name;
    private String region;
    private String country;
    private double lat;
    private double lon;
    private String tz_id;
    private long localtime_epoch;
    private String localtime;
}

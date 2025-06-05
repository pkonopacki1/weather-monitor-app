package com.pkonopacki1.weather_monitor_app_consumer.rest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Condition {
    private String text;
    private String icon;
    private int code;
}

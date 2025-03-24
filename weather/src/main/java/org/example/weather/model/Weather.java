package org.example.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;
}

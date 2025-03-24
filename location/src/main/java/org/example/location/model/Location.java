package org.example.location.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Location {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String cityName;

    @NonNull private double latitude;

    @NonNull private double longitude;

    public Location(@NonNull String cityName, @NonNull double latitude, @NonNull double longitude){
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}

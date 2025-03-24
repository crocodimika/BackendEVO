package org.example.weather.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {

    private static final String API_KEY = "5d32cc23657aedb00385664734a5c874";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    @GetMapping("/weather")
    public ResponseEntity<String> getWeather(
            @RequestParam("lat") double latitude,
            @RequestParam("lon") double longitude
    ) {
        String url = API_URL + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY + "&units=metric";

        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("Ошибка при запросе к OpenWeather: " + e.getMessage());
            return ResponseEntity.status(500).body("Ошибка при запросе к OpenWeather: " + e.getMessage());
        }
    }
}

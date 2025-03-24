package org.example.weather.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.example.weather.model.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.weather}")
    private String apiUrl;

    @Value("${appid}")
    private String apiKey;

    public WeatherController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/weather")
    @Cacheable(value = "weather", key = "#latitude + ',' + #longitude")
    public Root getWeather(@RequestParam("lat") double latitude,
                           @RequestParam("lon") double longitude) {
        String url = String.format("%s?lat=%f&lon=%f&appid=%s&units=metric",
                apiUrl, latitude, longitude, apiKey);
        return restTemplate.getForObject(url, Root.class);
    }
}

package org.example.location.controller;

import org.example.location.model.Location;
import org.example.location.model.Weather;
import org.example.location.repository.LocationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationRepository repository;
    private final RestTemplate restTemplate;

    public LocationController(LocationRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public Iterable<Location> findAll() {
        return repository.findAll();
    }

    @GetMapping(params = "name")
    public ResponseEntity<Location> findByName(@RequestParam String name) {
        return repository.findByNameIgnoreCase(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Location> save(@RequestBody Location location) {
        if (repository.findByNameIgnoreCase(location.getName()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Location savedLocation = repository.save(location);
        return ResponseEntity.ok(savedLocation);
    }

    @PutMapping(params = "name")
    public ResponseEntity<Location> update(@RequestParam String name, @RequestBody Location updatedLocation) {
        Optional<Location> existingLocation = repository.findByNameIgnoreCase(name);
        if (existingLocation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Location location = existingLocation.get();
        location.setLatitude(updatedLocation.getLatitude());
        location.setLongitude(updatedLocation.getLongitude());
        repository.save(location);
        return ResponseEntity.ok(location);
    }

    @DeleteMapping(params = "name")
    public ResponseEntity<Void> delete(@RequestParam String name) {
        Optional<Location> existingLocation = repository.findByNameIgnoreCase(name);
        if (existingLocation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(existingLocation.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/weather")
    public ResponseEntity<Weather> getWeather(@RequestParam String name) {
        Optional<Location> location = repository.findByNameIgnoreCase(name);
        if (location.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        String url = String.format("http://localhost:8082/weather?lat=%s&lon=%s",
                location.get().getLatitude(),
                location.get().getLongitude());
        Weather weather = restTemplate.getForObject(url, Weather.class);
        return ResponseEntity.ok(weather);
    }
}

package org.example.location.controller;

import org.example.location.model.Location;
import org.example.location.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public Iterable<Location> findAll() {
        return locationService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Location> findById(@PathVariable Long id) {
        return locationService.findById(id);
    }

    @GetMapping("/city/{cityName}")
    public ResponseEntity<Location> findByCityName(@PathVariable String cityName) {
        return locationService.findByCityName(cityName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Location> save(@RequestBody Location location) {
        return locationService.save(location);
    }
}
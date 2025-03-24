package org.example.location.service;

import org.example.location.model.Location;
import org.example.location.repository.LocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public Iterable<Location> findAll() {
        return repository.findAll();
    }

    public Optional<Location> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Location> findByCityName(String cityName) {
        return repository.findByCityNameIgnoreCase(cityName);
    }

    public ResponseEntity<Location> save(Location location) {
        if (location.getId() != null && repository.findById(location.getId()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Location savedLocation = repository.save(location);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLocation);
    }


}
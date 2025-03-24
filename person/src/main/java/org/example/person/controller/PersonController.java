package org.example.person.controller;

import org.example.person.model.Person;
import org.example.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String LOCATION_SERVICE_URL = "http://localhost:8082/locations/city/";
    private static final String WEATHER_SERVICE_URL = "http://localhost:8083/weather?lat=%f&lon=%f";


    @GetMapping
    public Iterable<Person> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Person> person = repository.findById(id);
        if (person.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String cityName = person.get().getLocation();
        ResponseEntity<LocationResponse> locationResponse = restTemplate.getForEntity(
                LOCATION_SERVICE_URL + cityName, LocationResponse.class);

        if (!locationResponse.getStatusCode().is2xxSuccessful() || locationResponse.getBody() == null) {
            return ResponseEntity.badRequest().body("Не удалось найти координаты для города " + cityName);
        }

        double latitude = locationResponse.getBody().latitude;
        double longitude = locationResponse.getBody().longitude;

        ResponseEntity<String> weatherResponse = restTemplate.getForEntity(
                String.format(WEATHER_SERVICE_URL, latitude, longitude).replace(',', '.'), String.class);


        return weatherResponse.getStatusCode().is2xxSuccessful()
                ? ResponseEntity.ok(weatherResponse.getBody())
                : ResponseEntity.badRequest().body("Ошибка при получении погоды");
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return repository.findById(person.getId()).isPresent()
                ? new ResponseEntity(repository.findById(person.getId()), HttpStatus.BAD_REQUEST)
                : new ResponseEntity(repository.save(person), HttpStatus.CREATED);
    }

    private static class LocationResponse {
        public double latitude;
        public double longitude;
    }
}
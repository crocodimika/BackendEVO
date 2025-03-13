package org.example.restdemo.controller;

import org.example.restdemo.dto.Person;
import org.example.restdemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @GetMapping("/person")
    public Iterable<Person> getPersons() {
        return repository.findAll();
    }

    @GetMapping("/person/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        return repository.save(person);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        if (repository.existsById(id)) {
            person.setId(id);
            repository.save(person);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            person.setId(id);
            repository.save(person);
            return new ResponseEntity<>(person, HttpStatus.CREATED);
        }
    }


    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable int id) {
        repository.deleteById(id);
    }
}

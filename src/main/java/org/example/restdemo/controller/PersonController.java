package org.example.restdemo.controller;

import org.example.restdemo.dto.Message;
import org.example.restdemo.dto.Person;
import org.example.restdemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private PersonService service;

    @GetMapping("/person")
    public Iterable<Person> getPersons() {
        return service.getAllPersons();
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable int id) {
        Optional<Person> person = Optional.ofNullable(service.findPersonById(id));
        if (person.isPresent()) {
            return new ResponseEntity<>(person.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        return service.addPerson(person);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        Person updatedPerson = service.updatePerson(id, person);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable int id) {
        service.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/person/{p_id}/message")
    public ResponseEntity<List<Message>> getMessagesFromPerson(@PathVariable int p_id) {
        List<Message> messages = service.getMessagesFromPerson(p_id);
        if (messages != null) {
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/person/{p_id}/message/{m_id}")
    public ResponseEntity<Message> getMessageFromPerson(@PathVariable int p_id, @PathVariable int m_id) {
        Message message = service.getMessageFromPerson(p_id, m_id);
        if (message != null) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/person/{p_id}/message")
    public ResponseEntity<Person> addMessage(@PathVariable int p_id, @RequestBody Message message) {
        Person updatedPerson = service.addMessageToPerson(p_id, message);
        if (updatedPerson != null) {
            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/person/{p_id}/message/{m_id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int p_id, @PathVariable int m_id) {
        boolean deleted = service.deleteMessageFromPerson(p_id, m_id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

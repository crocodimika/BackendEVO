package org.example.restdemo.controller;

import org.example.restdemo.dto.Message;
import org.example.restdemo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    private MessageRepository repository;

    @GetMapping("/message")
    public Iterable<Message> getMessages() {
        return repository.findAll();
    }

    @GetMapping("/message/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping("/message")
    public Message addMessage(@RequestBody Message message) {
        message.setTime(LocalDateTime.now());
        return repository.save(message);
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
        if (repository.existsById(id)) {
            message.setId(id);
            repository.save(message);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message.setId(id);
            repository.save(message);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/message/{id}")
    public void deleteMessage(@PathVariable int id) {
        repository.deleteById(id);
    }
}

package org.example.restdemo.service;

import org.example.restdemo.dto.Person;
import org.example.restdemo.dto.Message;
import org.example.restdemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    public List<Person> getAllPersons() {
        return (List<Person>) repository.findAll();
    }

    public Person findPersonById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Person addPerson(Person person) {
        return repository.save(person);
    }

    public Person updatePerson(int id, Person person) {
        person.setId(id);
        return repository.save(person);
    }

    public boolean deletePerson(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Person addMessageToPerson(int p_id, Message message) {
        Optional<Person> optionalPerson = repository.findById(p_id);

        if (optionalPerson.isEmpty()) {
            return null;
        }

        Person person = optionalPerson.get();
        message.setPerson(person);
        message.setTime(LocalDateTime.now());
        person.addMessage(message);

        return repository.save(person);
    }

    public boolean deleteMessageFromPerson(int p_id, int m_id) {
        Optional<Person> optionalPerson = repository.findById(p_id);

        if (optionalPerson.isEmpty()) {
            return false;
        }

        Person person = optionalPerson.get();
        List<Message> messages = person.getMessages();

        Iterator<Message> iterator = messages.iterator();
        while (iterator.hasNext()) {
            Message message = iterator.next();
            if (message.getId() == m_id) {
                iterator.remove();
                repository.save(person);
                return true;
            }
        }
        return false;
    }

    public List<Message> getMessagesFromPerson(int p_id) {
        Optional<Person> person = repository.findById(p_id);

        if (person.isPresent()) {
            return person.get().getMessages();
        } else {
            return Collections.emptyList();
        }
    }

    public Message getMessageFromPerson(int p_id, int m_id) {
        Optional<Person> person = repository.findById(p_id);

        if (person.isPresent()) {
            for (Message message : person.get().getMessages()) {
                if (message.getId() == m_id) {
                    return message;
                }
            }
        }
        return null;
    }
}

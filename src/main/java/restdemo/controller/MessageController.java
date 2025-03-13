package restdemo.controller;

import restdemo.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private List<Message> messages = new ArrayList<>(List.of(
            new Message(1, "Тест", "Тестовое сообщение 1", LocalDateTime.now()),
            new Message(2, "Привет", "Всем Привет! Коллеги.", LocalDateTime.now()),
            new Message(3, "Вопросы", "Что?Где?Когда?", LocalDateTime.now())
    ));

    @GetMapping
    public List<Message> getMessages() {
        return messages;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findMessageById(@PathVariable int id) {
        return messages.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        messages.add(message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getId() == id) {
                messages.set(i, message);
                return ResponseEntity.ok(message);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int id) {
        if (messages.removeIf(m -> m.getId() == id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

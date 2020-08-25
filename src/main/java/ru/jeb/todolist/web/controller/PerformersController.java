package ru.jeb.todolist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jeb.todolist.model.Performer;
import ru.jeb.todolist.service.TodoService;

import java.util.List;

/**
 * @author Jeb
 */
@RestController
@RequestMapping("/performers")
public class PerformersController {
    private final TodoService todoService;

    @Autowired
    public PerformersController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Performer>> performers() {
        return ResponseEntity.ok(todoService.findAllPerformers());
    }
}

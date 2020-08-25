package ru.jeb.todolist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jeb.todolist.model.NewTask;
import ru.jeb.todolist.model.Task;
import ru.jeb.todolist.model.UpdatePriority;
import ru.jeb.todolist.service.TodoService;

import java.util.List;

/**
 * @author Jeb
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TodoService todoService;

    @Autowired
    public TaskController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> listTask() {
        return ResponseEntity.ok(todoService.findAllTasks());
    }

    @PostMapping
    public void addTask(@RequestBody NewTask newTask) {
        todoService.addTask(newTask);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable long taskId) {
        todoService.deleteTask(taskId);
    }

    @PutMapping("/priority/{taskId}")
    public void updatePriorityTask(@PathVariable long taskId, @RequestBody UpdatePriority updatePriority) {
        todoService.changePriorityTask(taskId, updatePriority.getOperation());
    }

}

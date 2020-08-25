package ru.jeb.todolist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jeb.todolist.model.NewTask;
import ru.jeb.todolist.model.Performer;
import ru.jeb.todolist.model.PriorityOperation;
import ru.jeb.todolist.model.Task;
import ru.jeb.todolist.repository.PerformerRepository;
import ru.jeb.todolist.repository.TaskRepository;
import ru.jeb.todolist.service.TodoService;

import java.util.List;

/**
 * @author Jeb
 */
@Service
public class DefaultTodoService implements TodoService {
    private final TaskRepository taskRepository;
    private final PerformerRepository performerRepository;

    @Autowired
    public DefaultTodoService(TaskRepository taskRepository,
                              PerformerRepository performerRepository) {
        this.taskRepository = taskRepository;
        this.performerRepository = performerRepository;
    }

    @Override
    public List<Performer> findAllPerformers() {
        return performerRepository.findAll();
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTask(long id) {
        taskRepository.delete(id);
    }

    @Override
    public void changePriorityTask(long id, PriorityOperation operation) {
        taskRepository.changePriority(id, operation);
    }

    @Override
    public void addTask(NewTask newTask) {
        taskRepository.save(newTask);
    }
}

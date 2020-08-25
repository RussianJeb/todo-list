package ru.jeb.todolist.service;

import ru.jeb.todolist.model.NewTask;
import ru.jeb.todolist.model.Performer;
import ru.jeb.todolist.model.PriorityOperation;
import ru.jeb.todolist.model.Task;

import java.util.List;

/**
 * @author Jeb
 */
public interface TodoService {
    List<Performer> findAllPerformers();

    List<Task> findAllTasks();

    void deleteTask(long id);

    void changePriorityTask(long id, PriorityOperation operation);

    void addTask(NewTask newTask);
}

package ru.jeb.todolist.repository;

import ru.jeb.todolist.model.NewTask;
import ru.jeb.todolist.model.PriorityOperation;
import ru.jeb.todolist.model.Task;

import java.util.List;

/**
 * @author Jeb
 */
public interface TaskRepository {
    List<Task> findAll();

    void save(NewTask newTask);

    void changePriority(long id, PriorityOperation operation);

    void delete(long id);
}

package ru.jeb.todolist.repository;

import ru.jeb.todolist.model.Performer;

import java.util.List;

/**
 * @author Jeb
 */
public interface PerformerRepository {
    List<Performer> findAll();
}

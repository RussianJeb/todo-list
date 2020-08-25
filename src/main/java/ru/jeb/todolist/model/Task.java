package ru.jeb.todolist.model;

import lombok.Value;

/**
 * @author Jeb
 */
@Value
public class Task {
    private long id;
    private String description;
    private int priority;
    private String performer;
}

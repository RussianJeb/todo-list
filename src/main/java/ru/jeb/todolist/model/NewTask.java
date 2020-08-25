package ru.jeb.todolist.model;

import lombok.Value;

/**
 * @author Jeb
 */
@Value
public class NewTask {
    private String name;
    private String description;
    private int priority;
    private long performer;
}

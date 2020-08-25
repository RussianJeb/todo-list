package ru.jeb.todolist.repository.impl;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.jeb.todolist.model.NewTask;
import ru.jeb.todolist.model.PriorityOperation;
import ru.jeb.todolist.model.Task;
import ru.jeb.todolist.repository.TaskRepository;

import java.util.List;

import static ru.jeb.todolist.data.tables.Performers.PERFORMERS;
import static ru.jeb.todolist.data.tables.Task.TASK;

/**
 * @author Jeb
 */
@Repository
public class JOOQTaskRepository implements TaskRepository {
    private final DSLContext context;

    public JOOQTaskRepository(@Qualifier("dsl") DSLContext context) {
        this.context = context;
    }

    @Override
    public List<Task> findAll() {
        return context.select(TASK.ID, TASK.NAME, TASK.DESCRIPTION, TASK.PRIORITY, PERFORMERS.NAME)
                .from(TASK)
                .join(PERFORMERS)
                .on(TASK.PERFORMER_ID.eq(PERFORMERS.ID))
                .fetch()
                .into(Task.class);
    }

    @Override
    public void save(NewTask newTask) {
        context.insertInto(TASK)
                .set(TASK.NAME, newTask.getName())
                .set(TASK.DESCRIPTION, newTask.getDescription())
                .set(TASK.PRIORITY, newTask.getPriority())
                .set(TASK.PERFORMER_ID, newTask.getPerformer())
                .execute();
    }

    @Override
    public void changePriority(long id, PriorityOperation operation) {
        context.update(TASK)
                .set(TASK.PRIORITY, operation.set(TASK.PRIORITY))
                .where(TASK.ID.eq(id), operation.condition(TASK.PRIORITY))
                .execute();
    }

    @Override
    public void delete(long id) {
        context.delete(TASK)
                .where(TASK.ID.eq(id))
                .execute();
    }
}
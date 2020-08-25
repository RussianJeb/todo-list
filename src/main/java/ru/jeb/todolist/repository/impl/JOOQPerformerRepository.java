package ru.jeb.todolist.repository.impl;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.jeb.todolist.model.Performer;
import ru.jeb.todolist.repository.PerformerRepository;

import java.util.List;

import static ru.jeb.todolist.data.tables.Performers.PERFORMERS;


/**
 * @author Jeb
 */
@Repository
public class JOOQPerformerRepository implements PerformerRepository {
    private final DSLContext context;

    @Autowired
    public JOOQPerformerRepository(@Qualifier("dsl") DSLContext context) {
        this.context = context;
    }

    @Override
    public List<Performer> findAll() {
        return context.select()
                .from(PERFORMERS)
                .fetch()
                .into(Performer.class);
    }
}

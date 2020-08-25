package ru.jeb.todolist.model;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.TableField;

/**
 * @author Jeb
 */
public enum PriorityOperation {
    INCREMENT {
        @Override
        public <T extends Record> Field<Integer> set(TableField<T, Integer> field) {
            return field.add(1);
        }

        @Override
        public <T extends Record> Condition condition(TableField<T, Integer> field) {
            return field.lessThan(10);
        }
    },
    DECREMENT {
        @Override
        public <T extends Record> Field<Integer> set(TableField<T, Integer> field) {
            return field.sub(1);
        }

        @Override
        public <T extends Record> Condition condition(TableField<T, Integer> field) {
            return field.greaterThan(0);
        }
    };

    public abstract <T extends Record> Field<Integer> set(TableField<T, Integer> field);

    public abstract <T extends Record> Condition condition(TableField<T, Integer> field);

}

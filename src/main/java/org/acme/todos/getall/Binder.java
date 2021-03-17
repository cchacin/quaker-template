package org.acme.todos.getall;

import org.acme.model.Todo;

import javax.enterprise.inject.Produces;
import java.util.List;
import java.util.function.Supplier;

class Binder {

    @Produces
    Supplier<List<Todo>> getAllTodos() {
        return () -> DtoMapper.INSTANCE.mapFromDaoList(TodoEntity.findAll().list());
    }
}

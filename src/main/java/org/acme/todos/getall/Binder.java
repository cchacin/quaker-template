package org.acme.todos.getall;

import javax.enterprise.inject.Produces;
import java.util.List;
import java.util.function.Supplier;

public class Binder {

    @Produces
    Supplier<List<Endpoint.Todo>> getAllTodos() {
        return () -> DtoMapper.INSTANCE.mapFromDaoList(TodoEntity.findAll().list());
    }
}

package org.acme.todos.getall;

import org.mapstruct.factory.Mappers;

import javax.enterprise.inject.Produces;
import java.util.List;
import java.util.function.Supplier;

public class Binder {
    private final DtoMapper dtoMapper;
    private final Service service;

    public Binder() {
        this.dtoMapper = Mappers.getMapper(DtoMapper.class);
        this.service = new Service(
                id -> dtoMapper.mapFromDao(TodoEntity.findById(id)),
                () -> dtoMapper.mapFromDaoList(TodoEntity.findAll().list())
        );
    }

    @Produces
    Supplier<List<Endpoint.Todo>> getAllTodos() {
        return () -> dtoMapper.mapToRest(service.getAll());
    }
}

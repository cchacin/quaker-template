package org.acme.todos.getall;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "cdi")
interface DtoMapper {

    Service.Todo mapFromDao(TodoEntity entity);

    List<Service.Todo> mapFromDaoList(List<TodoEntity> entities);

    @Mapping(target = "task", source = "title")
    @Mapping(target = "created", source = "createdOn")
    Endpoint.Todo mapToRest(Service.Todo todo);

    List<Endpoint.Todo> mapToRest(List<Service.Todo> todos);
}

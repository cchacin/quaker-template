package org.acme.todos.getall;

import org.acme.model.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi")
interface DtoMapper {

    DtoMapper INSTANCE = Mappers.getMapper(DtoMapper.class);

    Todo mapFromDao(TodoEntity entity);

    List<Todo> mapFromDaoList(List<TodoEntity> entities);
}

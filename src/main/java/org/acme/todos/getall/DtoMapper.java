package org.acme.todos.getall;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi")
interface DtoMapper {

    DtoMapper INSTANCE = Mappers.getMapper(DtoMapper.class);

    @Mapping(target = "task", source = "title")
    @Mapping(target = "created", source = "createdOn")
    Endpoint.Todo mapFromDao(TodoEntity entity);

    List<Endpoint.Todo> mapFromDaoList(List<TodoEntity> entities);
}

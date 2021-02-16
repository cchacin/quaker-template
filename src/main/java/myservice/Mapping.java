package myservice;

import org.mapstruct.Mapper;
import org.openapitools.client.model.CreateTodoRequestBody;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "cdi")
public interface Mapping {

    Todo mapFromDao(DaoEntity entity);

    List<Todo> mapFromDaoList(List<DaoEntity> entity);

    Todo mapFromRest(CreateTodoRequestBody createTodoRequestBody);

    DaoEntity mapToDao(Todo todo);

    org.openapitools.client.model.Todo mapToRest(Todo todo);

    List<org.openapitools.client.model.Todo> mapToRest(List<Todo> todos);
}

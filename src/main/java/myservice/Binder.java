package myservice;

import org.mapstruct.factory.Mappers;
import org.openapitools.client.model.CreateTodoRequestBody;
import org.openapitools.client.model.Todo;

import javax.enterprise.inject.Produces;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Binder {
    private final Dao dao;
    private final Mapping mapping;
    private final Service service;

    public Binder() {
        this.dao = new Dao();
        this.mapping = Mappers.getMapper(Mapping.class);
        this.service = new Service(
                todo -> dao.persistAndFlush(mapping.mapToDao(todo)),
                id -> mapping.mapFromDao(dao.findById(id)),
                () -> mapping.mapFromDaoList(dao.findAll().list())
        );
    }

    @Produces
    @Transactional
    Consumer<CreateTodoRequestBody> save() {
        return body -> service.save(mapping.mapFromRest(body));
    }

    @Produces
    Supplier<List<Todo>> getAllTodos() {
        return () -> mapping.mapToRest(service.getAll());
    }
}

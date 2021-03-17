package org.acme.todos.getall;

import org.acme.api.ListTodosApi;
import org.acme.model.Todo;
import org.acme.model.TodoStatus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.function.Supplier;

@Path("/todos")
@ApplicationScoped
public class Endpoint implements ListTodosApi {

    private final Supplier<List<Todo>> getAllTodos;

    @Inject
    public Endpoint(
            final Supplier<List<Todo>> getAllTodos) {
        this.getAllTodos = getAllTodos;
    }

    @Override
    public Response listTodos(
            final TodoStatus status) {
        return Response.ok(getAllTodos.get()).build();
    }
}

package myservice;

import org.openapitools.client.api.ListTodosApi;
import org.openapitools.client.model.Todo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

@Path("/")
@ApplicationScoped
public class GetAllTodosEndpoint implements ListTodosApi {

    private final Supplier<List<Todo>> getAllTodos;

    @Inject
    public GetAllTodosEndpoint(
            final Supplier<List<Todo>> getAllTodos) {
        this.getAllTodos = getAllTodos;
    }

    @Override
    public CompletionStage<Response> listTodos(ListTodosParams params) throws WebApplicationException {
        return CompletableFuture.supplyAsync(
                () -> Response.ok(getAllTodos.get()).build()
        );
    }
}

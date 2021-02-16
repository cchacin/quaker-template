package myservice;

import org.openapitools.client.api.CreateTodoApi;
import org.openapitools.client.model.CreateTodoRequestBody;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

@Path("/")
@ApplicationScoped
public class SaveTodo implements CreateTodoApi {

    private final Consumer<CreateTodoRequestBody> create;

    @Inject
    public SaveTodo(
            final Consumer<CreateTodoRequestBody> create) {
        this.create = create;
    }

    @Override
    @Transactional
    public CompletionStage<Response> createTodo(
            final CreateTodoParams params,
            @Valid final CreateTodoRequestBody createTodoRequestBody) throws WebApplicationException {
        return CompletableFuture.supplyAsync(() -> {
            create.accept(createTodoRequestBody);
            return Response.created(params.coreUriInfo.getAbsolutePathBuilder().path("").build()).build();
        });
    }
}

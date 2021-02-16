package org.acme.todos.getall;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

@Path("/todos")
@Produces({"application/json"})
@ApplicationScoped
public class Endpoint {

    private final Supplier<List<Todo>> getAllTodos;

    @Inject
    public Endpoint(
            final Supplier<List<Todo>> getAllTodos) {
        this.getAllTodos = getAllTodos;
    }

    @GET
    public Response listTodos(
            @BeanParam final ListTodosParams params)
            throws WebApplicationException {
        return Response.ok(getAllTodos.get()).build();
    }

    @RegisterForReflection
    public static class Todo {
        private final Long id;
        private final String task;
        private final String status;
        private final LocalDateTime created;
        private final LocalDateTime doneAt;

        public Todo(
                final Long id,
                final String task,
                final String status,
                final LocalDateTime created,
                final LocalDateTime doneAt) {
            this.id = id;
            this.task = task;
            this.status = status;
            this.created = created;
            this.doneAt = doneAt;
        }

        public boolean isDone() {
            return doneAt != null;
        }

        public Long getId() {
            return id;
        }

        public String getTask() {
            return task;
        }

        public String getStatus() {
            return status;
        }

        public LocalDateTime getCreated() {
            return created;
        }

        public LocalDateTime getDoneAt() {
            return doneAt;
        }

        public static final class Builder {
            private Long id;
            private String task;
            private String status;
            private LocalDateTime createdOn;
            private LocalDateTime doneAt;

            private Builder() {
            }

            public static Builder aTodoItemEntity() {
                return new Builder();
            }

            public Builder id(Long id) {
                this.id = id;
                return this;
            }

            public Builder task(String task) {
                this.task = task;
                return this;
            }

            public Builder status(String status) {
                this.status = status;
                return this;
            }

            public Builder createdOn(LocalDateTime createdOn) {
                this.createdOn = createdOn;
                return this;
            }

            public Builder doneAt(LocalDateTime doneAt) {
                this.doneAt = doneAt;
                return this;
            }

            public Builder but() {
                return aTodoItemEntity().id(id).task(task).status(status).createdOn(createdOn).doneAt(doneAt);
            }

            public Todo build() {
                return new Todo(
                        id,
                        task,
                        status,
                        createdOn,
                        doneAt
                );
            }
        }

    }

    public enum TodoStatus {

        DONE("done"),

        WAITING("waiting"),

        WORKING("working");

        private final String value;

        TodoStatus(final String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return String.valueOf(this.value);
        }

        public static TodoStatus fromValue(final String value) {
            return java.util.Arrays.stream(TodoStatus.values())
                    .filter(b -> b.getValue().equals(value))
                    .findFirst()
                    .orElse(null);
        }
    }

    public static class ListTodosParams {
        @javax.ws.rs.core.Context
        public javax.ws.rs.core.HttpHeaders coreHttpHeaders;

        @javax.ws.rs.core.Context
        public javax.ws.rs.core.UriInfo coreUriInfo;

        @javax.ws.rs.QueryParam("status")
        public TodoStatus status;

        public ListTodosParams coreHttpHeaders(final javax.ws.rs.core.HttpHeaders coreHttpHeaders) {
            this.coreHttpHeaders = coreHttpHeaders;
            return this;
        }

        public ListTodosParams coreUriInfo(final javax.ws.rs.core.UriInfo coreUriInfo) {
            this.coreUriInfo = coreUriInfo;
            return this;
        }

        public ListTodosParams status(final TodoStatus status) {
            this.status = status;
            return this;
        }

    }
}

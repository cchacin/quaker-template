package org.acme.todos.getall;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
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
    public Response listTodos()
            throws WebApplicationException {
        return Response.ok(getAllTodos.get()).build();
    }

    @RegisterForReflection
    public static class Todo {
        private final Long id;
        private final String task;
        private final String status;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private final LocalDateTime created;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
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
}

package org.acme.todos.getall;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

class Service {

    private final Function<Long, Todo> getTodo;
    private final Supplier<List<Todo>> getTodos;

    public Service(
            final Function<Long, Todo> getTodo,
            final Supplier<List<Todo>> getTodos) {
        this.getTodo = getTodo;
        this.getTodos = getTodos;
    }

    public Todo get(final Long id) {
        return getTodo.apply(id);
    }

    public List<Todo> getAll() {
        return getTodos.get();
    }

    static final class Todo {
        private final Long id;
        private final String title;
        private final String status;
        private final LocalDateTime createdOn;
        private final LocalDateTime doneAt;

        public Todo(
                final Long id,
                final String title,
                final String status,
                final LocalDateTime createdOn,
                final LocalDateTime doneAt) {
            this.id = id;
            this.title = title;
            this.status = status;
            this.createdOn = createdOn;
            this.doneAt = doneAt;
        }

        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getStatus() {
            return status;
        }

        public LocalDateTime getCreatedOn() {
            return createdOn;
        }

        public LocalDateTime getDoneAt() {
            return doneAt;
        }

        public static Builder builder() {
            return Builder.aTodo();
        }

        public static final class Builder {
            private Long id;
            private String title;
            private String status;
            private LocalDateTime createdOn;
            private LocalDateTime doneAt;

            private Builder() {
            }

            public static Builder aTodo() {
                return new Builder();
            }

            public Builder id(final Long id) {
                this.id = id;
                return this;
            }

            public Builder title(final String title) {
                this.title = title;
                return this;
            }

            public Builder status(final String status) {
                this.status = status;
                return this;
            }

            public Builder createdOn(final LocalDateTime createdOn) {
                this.createdOn = createdOn;
                return this;
            }

            public Builder doneAt(final LocalDateTime doneAt) {
                this.doneAt = doneAt;
                return this;
            }

            public Builder but() {
                return aTodo().id(id).title(title).status(status).createdOn(createdOn).doneAt(doneAt);
            }

            public Todo build() {
                return new Todo(id, title, status, createdOn, doneAt);
            }
        }
    }
}

package org.acme.todos.getall;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
public class TodoEntity extends PanacheEntity {

    String title;

    String status;

    @Column(name = "created_on")
    LocalDateTime createdOn;

    @Column(name = "done_at")
    LocalDateTime doneAt;

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(final LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(final LocalDateTime doneAt) {
        this.doneAt = doneAt;
    }

    public static TodoEntity.Builder builder() {
        return Builder.aTodoItemEntity();
    }

    public static final class Builder {
        private Long id;
        private String title;
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

        public Builder title(String title) {
            this.title = title;
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
            return aTodoItemEntity().id(id).title(title).status(status).createdOn(createdOn).doneAt(doneAt);
        }

        public TodoEntity build() {
            TodoEntity todoEntity = new TodoEntity();
            todoEntity.setTitle(title);
            todoEntity.setStatus(status);
            todoEntity.setCreatedOn(createdOn);
            todoEntity.setDoneAt(doneAt);
            return todoEntity;
        }
    }
}

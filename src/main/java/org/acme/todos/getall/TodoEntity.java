package org.acme.todos.getall;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
class TodoEntity extends PanacheEntity {

    private String title;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "done_at")
    private LocalDateTime doneAt;

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
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

    public enum Status {
        DONE,
        WAITING,
        WORKING
    }
}

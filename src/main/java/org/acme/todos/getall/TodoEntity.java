package org.acme.todos.getall;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
class TodoEntity extends PanacheEntity {

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
}

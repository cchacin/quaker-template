package myservice;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todos")
public class DaoEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private String status;

    @Column(name = "created_on",
            columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdOn;

    @Column(name = "done_at",
            columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime doneAt;

    public DaoEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(LocalDateTime updatedAt) {
        this.doneAt = updatedAt;
    }

    public static DaoEntity.Builder builder() {
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

        public DaoEntity build() {
            DaoEntity daoEntity = new DaoEntity();
            daoEntity.setId(id);
            daoEntity.setTitle(title);
            daoEntity.setStatus(status);
            daoEntity.setCreatedOn(createdOn);
            daoEntity.setDoneAt(doneAt);
            return daoEntity;
        }
    }
}

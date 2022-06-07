package bg.manager.simpletaskmanager.entity.base;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer id;
    @Column(name = "project_key", nullable = false, unique = true)
    private String key;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "owner_id")
    private Account owner;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Project(Integer id, String key, String title, Account owner) {
        this.id = id;
        this.key = key;
        this.title = title;
        this.owner = owner;
    }

    public Project() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(key, project.key) && Objects.equals(title, project.title) && Objects.equals(owner, project.owner) && Objects.equals(createdAt, project.createdAt) && Objects.equals(updatedAt, project.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, key, title, owner, createdAt, updatedAt);
    }
}

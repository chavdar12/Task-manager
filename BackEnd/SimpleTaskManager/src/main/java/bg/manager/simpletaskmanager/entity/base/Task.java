package bg.manager.simpletaskmanager.entity.base;

import bg.manager.simpletaskmanager.entity.enums.Priorities;
import bg.manager.simpletaskmanager.entity.enums.Statuses;
import bg.manager.simpletaskmanager.entity.enums.Types;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "board_id")
    private Board board;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 50)
    private Types type;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status", nullable = false, length = 50)
    private Statuses status;

    @Column(name = "task_description", columnDefinition = "TEXT")
    private String description;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "reported_by")
    private Account reportedBy;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "assigned_to")
    private Account assignedTo;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, length = 50)
    private Priorities priority;

    @Column(name = "story_points")
    private Integer storyPoints;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Task(Integer id, String title, Project project, Board board, Types type, Statuses status, String description, Account reportedBy, Account assignedTo, Priorities priority, Integer storyPoints) {
        this.id = id;
        this.title = title;
        this.project = project;
        this.board = board;
        this.type = type;
        this.status = status;
        this.description = description;
        this.reportedBy = reportedBy;
        this.assignedTo = assignedTo;
        this.priority = priority;
        this.storyPoints = storyPoints;
    }

    public Task() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(Account reportedBy) {
        this.reportedBy = reportedBy;
    }

    public Account getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Account assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Priorities getPriority() {
        return priority;
    }

    public void setPriority(Priorities priority) {
        this.priority = priority;
    }

    public Integer getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(Integer storyPoints) {
        this.storyPoints = storyPoints;
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
}

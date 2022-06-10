package bg.manager.simpletaskmanager.entity.dto;

import bg.manager.simpletaskmanager.entity.enums.Priorities;
import bg.manager.simpletaskmanager.entity.enums.Statuses;
import bg.manager.simpletaskmanager.entity.enums.Types;

import java.io.Serializable;

public class TaskDto implements Serializable {
    private Integer id;
    private String title;
    private ProjectDto project;
    private BoardDto board;
    private Types type;
    private Statuses status;
    private String description;
    private AccountDto reportedBy;
    private AccountDto assignedTo;
    private Priorities priority;
    private Integer storyPoints;

    public TaskDto() {
    }

    public TaskDto(Integer id, String title, ProjectDto project, BoardDto board, Types type, Statuses status, String description, AccountDto reportedBy, AccountDto assignedTo, Priorities priority, Integer storyPoints) {
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

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public BoardDto getBoard() {
        return board;
    }

    public void setBoard(BoardDto board) {
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

    public AccountDto getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(AccountDto reportedBy) {
        this.reportedBy = reportedBy;
    }

    public AccountDto getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(AccountDto assignedTo) {
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
}

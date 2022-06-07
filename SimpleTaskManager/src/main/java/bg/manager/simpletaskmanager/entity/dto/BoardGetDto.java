package bg.manager.simpletaskmanager.entity.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class BoardGetDto implements Serializable {
    private Integer id;
    private String name;
    private AccountDto owner;
    private Set<AccountDto> members;
    private List<TaskDto> tasks;
    private List<ProjectDto> projects;

    public BoardGetDto() {
    }

    public BoardGetDto(Integer id, String name, AccountDto owner, Set<AccountDto> members) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.members = members;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountDto getOwner() {
        return owner;
    }

    public void setOwner(AccountDto owner) {
        this.owner = owner;
    }

    public Set<AccountDto> getMembers() {
        return members;
    }

    public void setMembers(Set<AccountDto> members) {
        this.members = members;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }

    public List<ProjectDto> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDto> projects) {
        this.projects = projects;
    }
}

package bg.manager.simpletaskmanager.entity.dto;

import java.io.Serializable;
import java.util.List;

public class ProjectGetDto implements Serializable {
    private Integer id;
    private String key;
    private String title;
    private AccountDto owner;
    private List<TaskDto> tasks;

    public ProjectGetDto() {
    }

    public ProjectGetDto(Integer id, String key, String title, AccountDto owner) {
        this.id = id;
        this.key = key;
        this.title = title;
        this.owner = owner;
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

    public AccountDto getOwner() {
        return owner;
    }

    public void setOwner(AccountDto owner) {
        this.owner = owner;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }
}

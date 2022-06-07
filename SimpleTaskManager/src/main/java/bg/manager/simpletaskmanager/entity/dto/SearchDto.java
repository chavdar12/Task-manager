package bg.manager.simpletaskmanager.entity.dto;

import bg.manager.simpletaskmanager.entity.enums.Types;

import java.io.Serializable;

public class SearchDto implements Serializable {
    private String title;
    private Types type;
    private AccountDto assignedTo;

    public SearchDto() {
    }

    public SearchDto(String title, Types type, AccountDto assignedTo) {
        this.title = title;
        this.type = type;
        this.assignedTo = assignedTo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public AccountDto getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(AccountDto assignedTo) {
        this.assignedTo = assignedTo;
    }
}

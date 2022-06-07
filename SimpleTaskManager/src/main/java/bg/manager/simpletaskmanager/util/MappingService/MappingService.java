package bg.manager.simpletaskmanager.util.MappingService;

import bg.manager.simpletaskmanager.entity.base.Account;
import bg.manager.simpletaskmanager.entity.base.Board;
import bg.manager.simpletaskmanager.entity.base.Project;
import bg.manager.simpletaskmanager.entity.base.Task;
import bg.manager.simpletaskmanager.entity.dto.*;

import java.util.List;
import java.util.stream.Collectors;

public class MappingService {

    //map account to account dto
    public static AccountDto mapAccountToAccountDto(Account account) {
        return new AccountDto(account.getId(), account.getFirstName(), account.getLastName(), account.getEmail());
    }

    //map account dto to account
    public static Account mapAccountDtoToAccount(AccountDto accountDto) {
        return new Account(accountDto.getId(), accountDto.getFirstName(), accountDto.getLastName(), accountDto.getEmail());
    }

    //map account list to account dto list
    public static List<AccountDto> mapAccountListToAccountDtoList(List<Account> accountList) {
        return accountList.stream().map(MappingService::mapAccountToAccountDto).collect(Collectors.toList());
    }

    //map account dto list to account list
    public static List<Account> mapAccountDtoListToAccountList(List<AccountDto> accountDtoList) {
        return accountDtoList.stream().map(MappingService::mapAccountDtoToAccount).collect(Collectors.toList());
    }

    //map board to board dto
    public static BoardDto mapBoardToBoardDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setName(board.getName());
        boardDto.setOwner(mapAccountToAccountDto(board.getOwner()));
        boardDto.setMembers(board.getMembers().stream().map(member -> mapAccountToAccountDto(member.getAccount())).collect(Collectors.toSet()));
        return boardDto;
    }

    //map board dto to board
    public static Board mapBoardDtoToBoard(BoardDto boardDto) {
        return new Board(boardDto.getId(), boardDto.getName(), mapAccountDtoToAccount(boardDto.getOwner()));
    }

    //map board list to board dto list
    public static List<BoardDto> mapBoardListToBoardDtoList(List<Board> boardList) {
        return boardList.stream().map(MappingService::mapBoardToBoardDto).collect(Collectors.toList());
    }

    //map board dto list to board list
    public static List<Board> mapBoardDtoListToBoardList(List<BoardDto> boardDtoList) {
        return boardDtoList.stream().map(MappingService::mapBoardDtoToBoard).collect(Collectors.toList());
    }

    //map project to project dto
    public static ProjectDto mapProjectToProjectDto(Project project) {
        return new ProjectDto(project.getId(), project.getKey(), project.getTitle(), mapAccountToAccountDto(project.getOwner()));
    }

    //map project dto to project
    public static Project mapProjectDtoToProject(ProjectDto projectDto) {
        return new Project(projectDto.getId(), projectDto.getKey(), projectDto.getTitle(), mapAccountDtoToAccount(projectDto.getOwner()));
    }

    //map project list to project dto list
    public static List<ProjectDto> mapProjectListToProjectDtoList(List<Project> projectList) {
        return projectList.stream().map(MappingService::mapProjectToProjectDto).collect(Collectors.toList());
    }

    //map project dto list to project list
    public static List<Project> mapProjectDtoListToProjectList(List<ProjectDto> projectDtoList) {
        return projectDtoList.stream().map(MappingService::mapProjectDtoToProject).collect(Collectors.toList());
    }

    //map task to task dto
    public static TaskDto mapTaskToTaskDto(Task task) {
        return new TaskDto(task.getId(),
                task.getTitle(),
                mapProjectToProjectDto(task.getProject()),
                mapBoardToBoardDto(task.getBoard()),
                task.getType(),
                task.getStatus(),
                task.getDescription(),
                mapAccountToAccountDto(task.getReportedBy()),
                mapAccountToAccountDto(task.getAssignedTo()),
                task.getPriority(),
                task.getStoryPoints());
    }

    //map task dto to task
    public static Task mapTaskDtoToTask(TaskDto taskDto) {
        return new Task(taskDto.getId(),
                taskDto.getTitle(),
                mapProjectDtoToProject(taskDto.getProject()),
                mapBoardDtoToBoard(taskDto.getBoard()),
                taskDto.getType(),
                taskDto.getStatus(),
                taskDto.getDescription(),
                mapAccountDtoToAccount(taskDto.getReportedBy()),
                mapAccountDtoToAccount(taskDto.getAssignedTo()),
                taskDto.getPriority(),
                taskDto.getStoryPoints());
    }

    //map task list to task dto list
    public static List<TaskDto> mapTaskListToTaskDtoList(List<Task> taskList) {
        return taskList.stream().map(MappingService::mapTaskToTaskDto).collect(Collectors.toList());
    }

    //map task dto list to task list
    public static List<Task> mapTaskDtoListToTaskList(List<TaskDto> taskDtoList) {
        return taskDtoList.stream().map(MappingService::mapTaskDtoToTask).collect(Collectors.toList());
    }

    //map project to projectGetDto
    public static ProjectGetDto mapProjectToProjectGetDto(Project project) {
        return new ProjectGetDto(project.getId(), project.getKey(), project.getTitle(), mapAccountToAccountDto(project.getOwner()));
    }

    //map board to boardGetDto
    public static BoardGetDto mapBoardToBoardGetDto(Board board) {
        return new BoardGetDto(board.getId(), board.getName(), mapAccountToAccountDto(board.getOwner()), board.getMembers().stream().map(member -> mapAccountToAccountDto(member.getAccount())).collect(Collectors.toSet()));
    }
}

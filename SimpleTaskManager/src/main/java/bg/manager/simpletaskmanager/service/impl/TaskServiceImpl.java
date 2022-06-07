package bg.manager.simpletaskmanager.service.impl;

import bg.manager.simpletaskmanager.entity.base.Account;
import bg.manager.simpletaskmanager.entity.base.Task;
import bg.manager.simpletaskmanager.entity.dto.SearchDto;
import bg.manager.simpletaskmanager.entity.dto.TaskDto;
import bg.manager.simpletaskmanager.entity.enums.Priorities;
import bg.manager.simpletaskmanager.entity.enums.Statuses;
import bg.manager.simpletaskmanager.entity.enums.Types;
import bg.manager.simpletaskmanager.repository.*;
import bg.manager.simpletaskmanager.service.base.TaskService;
import bg.manager.simpletaskmanager.util.MappingService.MappingService;
import bg.manager.simpletaskmanager.util.exception.EntityNotFoundException;
import bg.manager.simpletaskmanager.util.exception.InvalidDataException;
import bg.manager.simpletaskmanager.util.validation.impl.TaskValidationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final AccountRepository accountRepository;
    private final ProjectRepository projectRepository;
    private final BoardRepository boardRepository;
    private final BoardMemberRepository boardMemberRepository;
    private final TaskValidationService taskValidationService;

    public TaskServiceImpl(TaskRepository taskRepository, AccountRepository accountRepository, ProjectRepository projectRepository, BoardRepository boardRepository, BoardMemberRepository boardMemberRepository, TaskValidationService taskValidationService) {
        this.taskRepository = taskRepository;
        this.accountRepository = accountRepository;
        this.projectRepository = projectRepository;
        this.boardRepository = boardRepository;
        this.boardMemberRepository = boardMemberRepository;
        this.taskValidationService = taskValidationService;
    }

    //get all tasks
    @Override
    public List<TaskDto> getAllTasks() {
        //map to dto
        return MappingService.mapTaskListToTaskDtoList(taskRepository.findAll());
    }

    //create task
    @Override
    public void createTask(TaskDto taskDto) {

        //validate task dto
        if (!taskValidationService.isValid(taskDto)) {
            throw new InvalidDataException("Task is not valid");
        }

        Task task = MappingService.mapTaskDtoToTask(taskDto);

        task.setProject(projectRepository.getProjectById(taskDto.getProject().getId()).orElseThrow(() -> new EntityNotFoundException("Project not found")));
        task.setBoard(boardRepository.getBoardById(taskDto.getBoard().getId()).orElseThrow(() -> new EntityNotFoundException("Board not found")));

        //check if reported by account exists and is board member and throw exception if not
        if (boardMemberRepository.getBoardMemberByAccountIdAndBoardId(taskDto.getReportedBy().getId(), task.getBoard().getId()).isEmpty()) {
            task.setReportedBy(accountRepository.getAccountById(taskDto.getReportedBy().getId()).orElseThrow(() -> new EntityNotFoundException("Account is not board member or board owner")));
        } else {
            throw new EntityNotFoundException("Account is not board member or board owner");
        }

        //check if assigned to account exists and is board member and throw exception if not
        if (taskDto.getAssignedTo() != null) {
            if (boardMemberRepository.getBoardMemberByAccountIdAndBoardId(taskDto.getAssignedTo().getId(), task.getBoard().getId()).isEmpty()) {
                task.setAssignedTo(accountRepository.getAccountById(taskDto.getAssignedTo().getId()).orElseThrow(() -> new EntityNotFoundException("Account is not board member or board owner")));
            } else {
                throw new EntityNotFoundException("Account is not board member or board owner");
            }
        }

        //if priority is not set set it to default
        if (taskDto.getPriority() == null) {
            task.setPriority(Priorities.LOW);
        }

        //if status is not set set it to default
        if (taskDto.getStatus() == null) {
            task.setStatus(Statuses.NEW);
        }

        //set created at and updated at
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        taskRepository.save(task);
    }

    //update task
    @Override
    public void updateTask(TaskDto taskDto) {

        //check if taskdto is valid
        if (!taskValidationService.isValid(taskDto)) {
            throw new InvalidDataException("Task is not valid");
        }

        //check if task exists
        Task task = taskRepository.getTaskById(taskDto.getId()).orElseThrow(() -> new EntityNotFoundException("Task not found"));

        task.setTitle(taskDto.getTitle());

        //check if project exists and update it
        task.setProject(projectRepository.getProjectById(taskDto.getProject().getId()).orElseThrow(() -> new EntityNotFoundException("Project not found")));

        //check if board exists and update it
        task.setBoard(boardRepository.getBoardById(taskDto.getBoard().getId()).orElseThrow(() -> new EntityNotFoundException("Board not found")));

        task.setType(taskDto.getType());

        //check if status is not set if not set it to default or update it
        if (taskDto.getStatus() == null) {
            task.setStatus(Statuses.NEW);
        } else {
            task.setStatus(taskDto.getStatus());
        }


        task.setDescription(taskDto.getDescription());

        //check if the new reported by account is part of the board and update it
        if (boardMemberRepository.getBoardMemberByAccountIdAndBoardId(taskDto.getReportedBy().getId(), task.getBoard().getId()).isPresent()) {
            task.setReportedBy(accountRepository.getAccountById(taskDto.getReportedBy().getId()).orElseThrow(() -> new EntityNotFoundException("Account is not board member or board owner")));
        } else {
            throw new IllegalArgumentException("Account is not board member or board owner");
        }

        //check if assigned to account is part of the board and update it
        if (taskDto.getAssignedTo() != null) {
            if (boardMemberRepository.getBoardMemberByAccountIdAndBoardId(taskDto.getAssignedTo().getId(), task.getBoard().getId()).isPresent()) {
                task.setAssignedTo(accountRepository.getAccountById(taskDto.getAssignedTo().getId()).orElseThrow(() -> new EntityNotFoundException("Account is not board member or board owner")));
            } else {
                throw new IllegalArgumentException("Account is not board member or board owner");
            }
        }

        //check if priority is not set if not set it to default or update it
        if (taskDto.getPriority() == null) {
            task.setPriority(Priorities.LOW);
        } else {
            task.setPriority(taskDto.getPriority());
        }

        task.setStoryPoints(taskDto.getStoryPoints());

        //set updated at
        task.setUpdatedAt(LocalDateTime.now());


        taskRepository.save(task);
    }

    //delete task
    @Override
    public void deleteTask(Integer id) {
        //check if task exists
        projectRepository.getProjectById(id).orElseThrow(() -> new EntityNotFoundException("Project with" + id + "not found"));

        projectRepository.deleteById(id);
    }

    //search tasks
    @Override
    public List<TaskDto> searchTasks(SearchDto searchDto) {

        String title = searchDto.getTitle();

        Types type = searchDto.getType();

        Account account = accountRepository.findById(searchDto.getAssignedTo().getId()).orElse(null);
        List<TaskDto> taskDtos = new ArrayList<>(MappingService.mapTaskListToTaskDtoList(taskRepository.searchTasks(title, type, account.getId())));

        if (title == null && type == null && account == null) {
            return MappingService.mapTaskListToTaskDtoList(taskRepository.findAll());
        } else {
            return taskDtos;
        }
    }
}

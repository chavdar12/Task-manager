package bg.manager.simpletaskmanager.service.impl;

import bg.manager.simpletaskmanager.entity.base.Board;
import bg.manager.simpletaskmanager.entity.base.Project;
import bg.manager.simpletaskmanager.entity.base.Task;
import bg.manager.simpletaskmanager.entity.dto.ProjectDto;
import bg.manager.simpletaskmanager.entity.dto.ProjectGetDto;
import bg.manager.simpletaskmanager.repository.AccountRepository;
import bg.manager.simpletaskmanager.repository.BoardRepository;
import bg.manager.simpletaskmanager.repository.ProjectRepository;
import bg.manager.simpletaskmanager.repository.TaskRepository;
import bg.manager.simpletaskmanager.service.base.ProjectService;
import bg.manager.simpletaskmanager.util.MappingService.MappingService;
import bg.manager.simpletaskmanager.util.exception.EntityNotFoundException;
import bg.manager.simpletaskmanager.util.exception.InvalidDataException;
import bg.manager.simpletaskmanager.util.validation.impl.ProjectValidationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final AccountRepository accountRepository;
    private final BoardRepository boardRepository;
    private final TaskRepository taskRepository;
    private final ProjectValidationService projectValidationService;

    public ProjectServiceImpl(ProjectRepository projectRepository, AccountRepository accountRepository, BoardRepository boardRepository, TaskRepository taskRepository, ProjectValidationService projectValidationService) {
        this.projectRepository = projectRepository;
        this.accountRepository = accountRepository;
        this.boardRepository = boardRepository;
        this.taskRepository = taskRepository;
        this.projectValidationService = projectValidationService;
    }

    //get all projects
    @Override
    public List<ProjectGetDto> getAllProjects() {

        //get all tasks associated with the project by owner id
        List<Task> tasks = taskRepository.findAllById(projectRepository.findAll().stream().map(Project::getId).collect(Collectors.toList()));

        //get all projects and map to dto
        return projectRepository.findAll().stream()
                .map(project -> {
                    ProjectGetDto projectDto = MappingService.mapProjectToProjectGetDto(project);
                    projectDto.setTasks(tasks.stream().map(MappingService::mapTaskToTaskDto).collect(Collectors.toList()));
                    return projectDto;
                })
                .collect(Collectors.toList());
    }

    //create project
    @Override
    public void createProject(ProjectDto projectDto) {

        //check if valid
        if (!projectValidationService.isValid(projectDto)) {
            throw new InvalidDataException("Invalid project data");
        }

        //check if key is unique
        if (projectRepository.findByKeyIgnoreCase(projectDto.getKey()).isPresent()) {
            throw new InvalidDataException("Project key is already taken");
        }

        Project project = MappingService.mapProjectDtoToProject(projectDto);

        //check if owner exists
        project.setOwner(accountRepository.findById(projectDto.getOwner().getId()).orElseThrow((() -> new EntityNotFoundException("Owner not found"))));

        //set created and updated at
        project.setCreatedAt(LocalDateTime.now());
        project.setUpdatedAt(LocalDateTime.now());

        projectRepository.save(project);
    }

    //update project
    @Override
    public void updateProject(ProjectDto projectDto) {

        //validate dto
        if (!projectValidationService.isValid(projectDto)) {
            throw new InvalidDataException("Invalid project data");
        }

        //check if new project key is unique
        if (projectRepository.findByKeyIgnoreCase(projectDto.getKey()).isPresent()) {
            throw new InvalidDataException("Project key is already taken");
        }


        Project project = projectRepository.findById(projectDto.getId()).orElseThrow(() -> new EntityNotFoundException("Project not found"));

        project.setKey(projectDto.getKey());
        project.setTitle(projectDto.getTitle());
        //set updated at
        project.setUpdatedAt(LocalDateTime.now());

        projectRepository.save(project);
    }

    @Override
    public void deleteProject(Integer id) {
        //delete all Tasks and the board only if all Board’s tasks are related to the deleted project

        //check with which boards the project owner is associated
        Set<Board> boards = boardRepository.findByOwnerId(id);

        //check if the project owner is associated with any board
        if (boards.size() == 0) {
            //delete all tasks
            taskRepository.deleteAllByProjectId(id);
            //delete the project
            projectRepository.deleteById(id);
        } else {
            //check if the project owner is associated with any board
            for (Board board : boards) {
                //check if the project owner is associated with any board
                Set<Task> tasks = taskRepository.findByBoardId(board.getId());
                //check if the project owner is associated with any board
                if (tasks.size() == 0) {
                    //delete the board
                    boardRepository.deleteById(board.getId());
                } else {
                    //check if the project owner is associated with any board
                    for (Task task : tasks) {
                        //check if the project owner is associated with any board
                        if (task.getProject().getId().equals(id)) {
                            //delete the task
                            taskRepository.deleteById(task.getId());
                        }
                    }
                }
            }
            //delete the project
            projectRepository.deleteById(id);
        }
    }
}

package bg.manager.simpletaskmanager.service.base;

import bg.manager.simpletaskmanager.entity.dto.ProjectDto;
import bg.manager.simpletaskmanager.entity.dto.ProjectGetDto;

import java.util.List;

//interface to hold all the methods
public interface ProjectService {
    List<ProjectGetDto> getAllProjects();

    void createProject(ProjectDto projectDto);

    void updateProject(ProjectDto projectDto);

    void deleteProject(Integer id);
}

package bg.manager.simpletaskmanager.service.base;

import bg.manager.simpletaskmanager.entity.dto.SearchDto;
import bg.manager.simpletaskmanager.entity.dto.TaskDto;

import java.util.List;

//interface to hold all the methods
public interface TaskService {
    List<TaskDto> getAllTasks();

    void createTask(TaskDto taskDto);

    void updateTask(TaskDto taskDto);

    void deleteTask(Integer id);

    List<TaskDto> searchTasks(SearchDto searchDto);
}

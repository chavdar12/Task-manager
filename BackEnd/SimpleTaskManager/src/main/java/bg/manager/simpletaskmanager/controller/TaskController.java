package bg.manager.simpletaskmanager.controller;

import bg.manager.simpletaskmanager.entity.dto.SearchDto;
import bg.manager.simpletaskmanager.entity.dto.TaskDto;
import bg.manager.simpletaskmanager.service.base.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createTask(@RequestBody TaskDto taskDto) {
        taskService.createTask(taskDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> updateTask(@RequestBody TaskDto taskDto) {
        taskService.updateTask(taskDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Integer id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TaskDto>> searchTasks(@RequestBody SearchDto searchDto) {
        return ResponseEntity.ok(taskService.searchTasks(searchDto));
    }
}
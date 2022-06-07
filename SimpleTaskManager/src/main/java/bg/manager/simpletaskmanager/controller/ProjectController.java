package bg.manager.simpletaskmanager.controller;

import bg.manager.simpletaskmanager.entity.dto.ProjectDto;
import bg.manager.simpletaskmanager.entity.dto.ProjectGetDto;
import bg.manager.simpletaskmanager.service.base.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectGetDto>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createProject(@RequestBody ProjectDto projectDto) {
        projectService.createProject(projectDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> updateProject(@RequestBody ProjectDto projectDto) {
        projectService.updateProject(projectDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

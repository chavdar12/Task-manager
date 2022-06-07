package bg.manager.simpletaskmanager.repository;

import bg.manager.simpletaskmanager.entity.base.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Optional<Project> findByKeyIgnoreCase(String key);

    Optional<Project> getProjectById(Integer id);
}
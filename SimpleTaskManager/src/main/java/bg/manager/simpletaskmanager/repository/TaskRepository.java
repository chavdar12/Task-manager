package bg.manager.simpletaskmanager.repository;

import bg.manager.simpletaskmanager.entity.base.Task;
import bg.manager.simpletaskmanager.entity.enums.Types;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> getTaskById(Integer id);

    void deleteAllByProjectId(Integer id);

    Set<Task> findByBoardId(Integer id);

    //query to check if title, type, assigned to are set and search by them
    @Query(value = "SELECT * FROM task WHERE title = ?1 AND type = ?2 AND assigned_to = ?3", nativeQuery = true)
    List<Task> searchTasks(@Param("title") String title, @Param("type") Types type, @Param("assigned_to") Integer assignedTo);
}
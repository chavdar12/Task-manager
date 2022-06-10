package bg.manager.simpletaskmanager.repository;

import bg.manager.simpletaskmanager.entity.base.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    Optional<Object> findByNameIgnoreCase(String name);

    Optional<Board> getBoardById(Integer id);

    Set<Board> findByOwnerId(Integer id);
}
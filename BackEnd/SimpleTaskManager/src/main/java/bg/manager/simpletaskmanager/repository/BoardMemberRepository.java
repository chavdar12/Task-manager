package bg.manager.simpletaskmanager.repository;

import bg.manager.simpletaskmanager.entity.base.BoardMember;
import bg.manager.simpletaskmanager.entity.base.BoardMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardMemberRepository extends JpaRepository<BoardMember, BoardMemberId> {

    void deleteAllByBoardId(Integer id);

    Optional<Object> getBoardMemberByAccountIdAndBoardId(Integer id, Integer id1);

}
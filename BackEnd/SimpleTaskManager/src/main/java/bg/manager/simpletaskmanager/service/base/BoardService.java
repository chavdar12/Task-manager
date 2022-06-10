package bg.manager.simpletaskmanager.service.base;

import bg.manager.simpletaskmanager.entity.dto.BoardDto;
import bg.manager.simpletaskmanager.entity.dto.BoardGetDto;

import java.util.List;

//interface to hold all the methods
public interface BoardService {
    List<BoardGetDto> getAllBoards();

    void createBoard(BoardDto boardDto);

    void updateBoard(BoardDto boardDto);

    void deleteBoard(Integer id);
}

package bg.manager.simpletaskmanager.controller;

import bg.manager.simpletaskmanager.entity.dto.BoardDto;
import bg.manager.simpletaskmanager.entity.dto.BoardGetDto;
import bg.manager.simpletaskmanager.service.base.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //get all boards
    @GetMapping("/all")
    public ResponseEntity<List<BoardGetDto>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }

    //create board with owner(account) and set of members(accounts)
    @PostMapping("/create")
    public ResponseEntity<Void> createBoard(@RequestBody BoardDto boardDto) {
        boardService.createBoard(boardDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //don't know why it has to be transactional
    //update board
    @Transactional
    @PatchMapping("/update")
    public ResponseEntity<Void> updateBoard(@RequestBody BoardDto boardDto) {
        boardService.updateBoard(boardDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //delete by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Integer id) {
        boardService.deleteBoard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

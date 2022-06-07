package bg.manager.simpletaskmanager.service.impl;

import bg.manager.simpletaskmanager.entity.base.*;
import bg.manager.simpletaskmanager.entity.dto.BoardDto;
import bg.manager.simpletaskmanager.entity.dto.BoardGetDto;
import bg.manager.simpletaskmanager.repository.*;
import bg.manager.simpletaskmanager.service.base.BoardService;
import bg.manager.simpletaskmanager.util.MappingService.MappingService;
import bg.manager.simpletaskmanager.util.exception.EntityNotFoundException;
import bg.manager.simpletaskmanager.util.exception.InvalidDataException;
import bg.manager.simpletaskmanager.util.validation.impl.BoardValidationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//I have no idea how to work with mapping class with composite primary keys
//this is the only way I found to do it
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final AccountRepository accountRepository;
    private final BoardMemberRepository boardMemberRepository;
    private final TaskRepository taskRepository;
    private final BoardValidationService boardValidationService;

    private final ProjectRepository projectRepository;

    public BoardServiceImpl(BoardRepository boardRepository,
                            AccountRepository accountRepository,
                            BoardMemberRepository boardMemberRepository,
                            TaskRepository taskRepository,
                            BoardValidationService boardValidationService,
                            ProjectRepository projectRepository) {
        this.boardRepository = boardRepository;
        this.accountRepository = accountRepository;
        this.boardMemberRepository = boardMemberRepository;
        this.taskRepository = taskRepository;
        this.boardValidationService = boardValidationService;
        this.projectRepository = projectRepository;
    }

    //get all boards
    @Override
    public List<BoardGetDto> getAllBoards() {

        //find all tasks related to the board by board id
        List<Task> tasks = taskRepository.findAllById(boardRepository.findAll().stream().map(Board::getId).collect(Collectors.toList()));

        //find all projects related to the board by owner id
        List<Project> projects = projectRepository.findAllById(boardRepository.findAll().stream().map(Board::getId).collect(Collectors.toList()));

        //get all boards
        List<Board> boards = boardRepository.findAll();

        //map all boards to dto
        return boards.stream().map(board -> {
            BoardGetDto boardGetDto = new BoardGetDto();
            boardGetDto.setId(board.getId());
            boardGetDto.setName(board.getName());
            boardGetDto.setOwner(MappingService.mapAccountToAccountDto(board.getOwner()));
            boardGetDto.setMembers(board.getMembers().stream().map(boardMember -> MappingService.mapAccountToAccountDto(boardMember.getAccount())).collect(Collectors.toSet()));
            boardGetDto.setTasks(tasks.stream().filter(task -> task.getBoard().getId().equals(board.getId())).map(MappingService::mapTaskToTaskDto).collect(Collectors.toList()));
            boardGetDto.setProjects(projects.stream().map(MappingService::mapProjectToProjectDto).collect(Collectors.toList()));
            return boardGetDto;
        }).collect(Collectors.toList());
    }

    //create new board
    @Override
    public void createBoard(BoardDto boardDto) {

        //validate board dto and throw exception if it is not valid
        if (!boardValidationService.isValid(boardDto)) {
            throw new InvalidDataException("Board is not valid");
        }

        //check if board with the same name already exists and throw exception if it does
        if (boardRepository.findByNameIgnoreCase(boardDto.getName()).isPresent()) {
            throw new InvalidDataException("Board with this name already exists");
        }

        Board board = new Board();

        board.setName(boardDto.getName());
        //set create and updated date because they are not null id db
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());

        board.setOwner(accountRepository.findById(boardDto.getOwner().getId()).orElseThrow(() -> new EntityNotFoundException("Owner not found")));

        boardRepository.save(board);

        BoardMember boardMember = new BoardMember();

        boardDto.getMembers().forEach(member -> {
            boardMember.setId(new BoardMemberId(board.getId(), member.getId()));
            boardMember.setAccount(accountRepository.findById(member.getId()).orElseThrow(() -> new EntityNotFoundException("Member not found")));
            boardMember.setBoard(boardRepository.findById(board.getId()).orElseThrow(() -> new EntityNotFoundException("Board not found")));
            boardMemberRepository.save(boardMember);
        });
    }

    //update board
    @Override
    public void updateBoard(BoardDto boardDto) {

        //check if board dto is valid and throw exception if it is not
        if (!boardValidationService.isValid(boardDto)) {
            throw new InvalidDataException("Board is not valid");
        }

        //find board by id and throw exception if it is not found
        Board board = boardRepository.findById(boardDto.getId()).orElseThrow(() -> new EntityNotFoundException("Board not found"));

        board.setName(boardDto.getName());
        board.setOwner(accountRepository.findById(boardDto.getOwner().getId()).orElseThrow(() -> new EntityNotFoundException("Owner not found")));
        //set updated at
        board.setUpdatedAt(LocalDateTime.now());

        boardRepository.save(board);

        //no idea how to update the members without deleting and creating new ones
        boardMemberRepository.deleteAllByBoardId(board.getId());

        boardDto.getMembers().forEach(member -> {
            BoardMember boardMember = new BoardMember();
            boardMember.setId(new BoardMemberId(board.getId(), member.getId()));
            boardMember.setAccount(accountRepository.findById(member.getId()).orElseThrow(() -> new EntityNotFoundException("Member not found")));
            boardMember.setBoard(boardRepository.findById(board.getId()).orElseThrow(() -> new EntityNotFoundException("Board not found")));
            boardMemberRepository.save(boardMember);
        });
    }

    //delete board
    @Override
    public void deleteBoard(Integer id) {
        //On Delete Board, delete all tasks

        //find all tasks related to the board and delete them
        taskRepository.deleteAll(taskRepository.findAllById(boardRepository.findAll().stream().map(Board::getId).collect(Collectors.toList())));

        boardRepository.deleteById(id);
    }
}

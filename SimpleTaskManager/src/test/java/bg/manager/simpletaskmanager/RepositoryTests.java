package bg.manager.simpletaskmanager;

import bg.manager.simpletaskmanager.entity.base.Account;
import bg.manager.simpletaskmanager.entity.base.Board;
import bg.manager.simpletaskmanager.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RepositoryTests {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BoardMemberRepository boardMemberRepository;


    @Test
    public void testCreateAccount() {
        Account account = new Account(1, "firstName", "lastName", "email", LocalDateTime.now(), LocalDateTime.now());
        accountRepository.save(account);

        Boolean account1 = accountRepository.existsById(1);

        assertThat(account1).isTrue();
    }

    @Test
    public void testDeleteAccount() {
        Account account = new Account(1, "firstName", "lastName", "email", LocalDateTime.now(), LocalDateTime.now());
        accountRepository.save(account);

        accountRepository.delete(account);

        Boolean account1 = accountRepository.existsById(1);

        assertThat(account1).isFalse();
    }

    @Test
    public void testGetAllAccounts() {
        Account account = new Account(1, "firstName", "lastName", "email", LocalDateTime.now(), LocalDateTime.now());
        Account account1 = new Account(2, "firstName1", "lastName1", "email1", LocalDateTime.now(), LocalDateTime.now());
        Account account2 = new Account(3, "firstName2", "lastName2", "email2", LocalDateTime.now(), LocalDateTime.now());

        List<Account> accounts = Arrays.asList(account, account1, account2);
        accountRepository.saveAll(accounts);

        List<Account> expected = accountRepository.findAll();

        assertThat(expected.size()).isEqualTo(3);
    }

    /*@Test
    public void boardCreate() {
        Account account = new Account(1, "firstName", "lastName", "email", LocalDateTime.now(), LocalDateTime.now());

        Board board = new Board(1, "test", account);
        boardRepository.save(board);

        Boolean board1 = boardRepository.existsById(1);

        assertThat(board1).isTrue();
    }*/


}

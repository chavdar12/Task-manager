package bg.manager.simpletaskmanager;

import bg.manager.simpletaskmanager.entity.base.Account;
import bg.manager.simpletaskmanager.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SimpleTaskManagerApplicationTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testCreateAccount() {
        Account account = new Account(1, "firstName", "lastName", "email", LocalDateTime.now(), LocalDateTime.now());
        accountRepository.save(account);

        Boolean account1 = accountRepository.existsById(1);

        assertThat(account1).isTrue();
    }

}

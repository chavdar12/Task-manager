package bg.manager.simpletaskmanager;

import bg.manager.simpletaskmanager.entity.base.Account;
import bg.manager.simpletaskmanager.repository.AccountRepository;
import bg.manager.simpletaskmanager.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {

    @Mock
    private AccountRepository accountRepository;

    private AccountServiceImpl accountService;

}

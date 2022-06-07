package bg.manager.simpletaskmanager.service.base;

import bg.manager.simpletaskmanager.entity.dto.AccountDto;

import java.util.List;

//interface to hold all the methods
public interface AccountService {
    List<AccountDto> getAllAccounts();

    void createAccount(AccountDto accountDto);

    void updateAccount(AccountDto accountDto);

    void deleteAccount(Integer id);
}

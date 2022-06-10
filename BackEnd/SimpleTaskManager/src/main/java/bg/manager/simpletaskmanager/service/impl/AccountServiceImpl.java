package bg.manager.simpletaskmanager.service.impl;

import bg.manager.simpletaskmanager.entity.base.Account;
import bg.manager.simpletaskmanager.entity.dto.AccountDto;
import bg.manager.simpletaskmanager.repository.AccountRepository;
import bg.manager.simpletaskmanager.service.base.AccountService;
import bg.manager.simpletaskmanager.util.MappingService.MappingService;
import bg.manager.simpletaskmanager.util.exception.EntityNotFoundException;
import bg.manager.simpletaskmanager.util.exception.InvalidDataException;
import bg.manager.simpletaskmanager.util.validation.impl.AccountValidationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountValidationService accountValidationService;

    public AccountServiceImpl(AccountRepository accountRepository, AccountValidationService accountValidationService) {
        this.accountRepository = accountRepository;
        this.accountValidationService = accountValidationService;
    }

    //get all accounts
    @Override
    public List<AccountDto> getAllAccounts() {
        //maps the accounts to list of accountDto
        return MappingService.mapAccountListToAccountDtoList(accountRepository.findAll());
    }

    //create account
    @Override
    public void createAccount(AccountDto accountDto) {

        //validate the accountDto if not valid throw exception
        if (!accountValidationService.isValid(accountDto)) {
            throw new InvalidDataException("Invalid account data");
        }

        //check if the account with the same username already exists ot throw exception
        if (accountRepository.findByEmailIgnoreCase(accountDto.getEmail()).isPresent()) {
            throw new InvalidDataException("Email is already taken");
        }

        //map the accountDto to account
        Account account = MappingService.mapAccountDtoToAccount(accountDto);

        //set the created date and updated date because both are not nullable
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());

        accountRepository.save(account);
    }

    //update account
    @Override
    public void updateAccount(AccountDto accountDto) {
        //check if dto is valid if not throw exception
        if (!accountValidationService.isValid(accountDto) && accountDto.getId() != null) {
            throw new InvalidDataException("Invalid account data");
        }

        //check if account exists with email and if so throw exception
        if (accountRepository.findByEmailIgnoreCase(accountDto.getEmail()).isPresent()) {
            throw new InvalidDataException("Email is already taken");
        }

        //check if account exists with id and if so throw exception
        Account account = accountRepository.findById(accountDto.getId()).orElseThrow(() -> new EntityNotFoundException("Account with id " + accountDto.getId() + " does not exist"));

        //set data
        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setEmail(accountDto.getEmail());

        //set update date
        account.setUpdatedAt(LocalDateTime.now());

        accountRepository.save(account);
    }

    //delete account
    @Override
    public void deleteAccount(Integer id) {

        //check if account exists with id and if so throw exception
        Account account = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account with id " + id + " does not exist"));

        accountRepository.delete(account);
    }
}

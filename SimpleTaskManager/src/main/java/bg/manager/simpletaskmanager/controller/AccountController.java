package bg.manager.simpletaskmanager.controller;

import bg.manager.simpletaskmanager.entity.dto.AccountDto;
import bg.manager.simpletaskmanager.service.base.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //get all accounts
    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    //create account with firstname, lastname, email
    @PostMapping("/create")
    public ResponseEntity<Void> createAccount(@RequestBody AccountDto accountDto) {
        accountService.createAccount(accountDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //update account
    @PatchMapping("/update")
    public ResponseEntity<Void> updateAccount(@RequestBody AccountDto accountDto) {
        accountService.updateAccount(accountDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //delete account
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

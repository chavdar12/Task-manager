package bg.manager.simpletaskmanager.util.validation.impl;


import bg.manager.simpletaskmanager.entity.dto.AccountDto;
import bg.manager.simpletaskmanager.util.validation.base.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class AccountValidationService implements ValidationService<AccountDto> {

    private static final String PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public boolean isValid(AccountDto entity) {
        return fieldArePresent(entity) && fieldAreValid(entity) && isAccountDto(entity);
    }

    private boolean isAccountDto(AccountDto entity) {
        return entity.getClass().getSimpleName().equals("AccountDto");
    }

    private boolean fieldAreValid(AccountDto entity) {
        return entity.getFirstName().length() > 0 && entity.getLastName().length() > 0 && entity.getEmail().length() > 0
                && entity.getEmail().matches(PATTERN);
    }

    private boolean fieldArePresent(AccountDto entity) {
        return entity.getFirstName() != null &&
                entity.getLastName() != null &&
                entity.getEmail() != null;
    }
}

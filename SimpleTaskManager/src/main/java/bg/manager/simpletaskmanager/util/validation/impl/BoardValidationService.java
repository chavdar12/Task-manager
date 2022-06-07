package bg.manager.simpletaskmanager.util.validation.impl;

import bg.manager.simpletaskmanager.entity.dto.BoardDto;
import bg.manager.simpletaskmanager.util.validation.base.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class BoardValidationService implements ValidationService<BoardDto> {

    private final AccountValidationService accountValidationService;

    public BoardValidationService(AccountValidationService accountValidationService) {
        this.accountValidationService = accountValidationService;
    }

    @Override
    public boolean isValid(BoardDto entity) {
        return fieldArePresent(entity) && fieldAreValid(entity) && isBoardDto(entity);
    }

    private boolean fieldAreValid(BoardDto entity) {
        return entity.getName().length() > 0 &&
                entity.getMembers().size() > 0 &&
                accountValidationService.isValid(entity.getOwner()) &&
                entity.getMembers().stream().allMatch(accountValidationService::isValid);
    }

    private boolean fieldArePresent(BoardDto entity) {
        return entity.getName() != null &&
                entity.getOwner() != null &&
                entity.getMembers() != null;
    }

    private boolean isBoardDto(BoardDto entity) {
        return entity.getClass().getSimpleName().equals("BoardDto");
    }
}

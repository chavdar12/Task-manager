package bg.manager.simpletaskmanager.util.validation.impl;

import bg.manager.simpletaskmanager.entity.dto.TaskDto;
import bg.manager.simpletaskmanager.util.validation.base.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class TaskValidationService implements ValidationService<TaskDto> {

    private final AccountValidationService accountValidationService;
    private final BoardValidationService boardValidationService;
    private final ProjectValidationService projectValidationService;

    public TaskValidationService(AccountValidationService accountValidationService, BoardValidationService boardValidationService, ProjectValidationService projectValidationService) {
        this.accountValidationService = accountValidationService;
        this.boardValidationService = boardValidationService;
        this.projectValidationService = projectValidationService;
    }

    @Override
    public boolean isValid(TaskDto entity) {
        return fieldArePresent(entity) && fieldAreValid(entity) && isTaskDto(entity);
    }

    private boolean isTaskDto(TaskDto entity) {
        return entity.getClass().getSimpleName().equals("TaskDto");
    }

    private boolean fieldAreValid(TaskDto entity) {
        return entity.getTitle().length() > 0 &&
                projectValidationService.isValid(entity.getProject()) &&
                boardValidationService.isValid(entity.getBoard()) &&
                accountValidationService.isValid(entity.getReportedBy());
    }

    private boolean fieldArePresent(TaskDto entity) {
        return entity.getTitle() != null &&
                entity.getReportedBy() != null &&
                entity.getProject() != null &&
                entity.getBoard() != null;
    }
}

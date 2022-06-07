package bg.manager.simpletaskmanager.util.validation.impl;

import bg.manager.simpletaskmanager.entity.dto.ProjectDto;
import bg.manager.simpletaskmanager.util.validation.base.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class ProjectValidationService implements ValidationService<ProjectDto> {

    private final AccountValidationService accountValidationService;

    public ProjectValidationService(AccountValidationService accountValidationService) {
        this.accountValidationService = accountValidationService;
    }

    @Override
    public boolean isValid(ProjectDto entity) {
        return fieldArePresent(entity) && fieldAreValid(entity) && isProjectDto(entity);
    }

    private boolean fieldArePresent(ProjectDto entity) {
        return entity.getKey() != null &&
                entity.getOwner() != null &&
                entity.getTitle() != null;
    }

    private boolean fieldAreValid(ProjectDto entity) {
        return entity.getKey().length() > 0 &&
                entity.getTitle().length() > 0 &&
                accountValidationService.isValid(entity.getOwner());
    }

    private boolean isProjectDto(ProjectDto entity) {
        return entity.getClass().getSimpleName().equals("ProjectDto");
    }
}

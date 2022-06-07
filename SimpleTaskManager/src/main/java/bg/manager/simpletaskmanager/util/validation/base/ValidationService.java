package bg.manager.simpletaskmanager.util.validation.base;

public interface ValidationService<T> {
    boolean isValid(T entity);
}

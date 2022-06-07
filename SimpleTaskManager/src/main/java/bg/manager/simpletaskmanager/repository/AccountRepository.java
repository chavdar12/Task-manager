package bg.manager.simpletaskmanager.repository;

import bg.manager.simpletaskmanager.entity.base.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Object> findByEmailIgnoreCase(String email);

    Optional<Account> getAccountById(Integer id);
}
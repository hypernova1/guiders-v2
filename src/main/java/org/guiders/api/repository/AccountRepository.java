package org.guiders.api.repository;

import org.guiders.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

    int countByEmail(String email);

    int countByEmailAndPassword(String email, String password);
}

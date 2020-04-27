package org.guiders.api.repository;

import org.guiders.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Account, Long> {

}

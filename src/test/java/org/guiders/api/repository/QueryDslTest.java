package org.guiders.api.repository;

import com.querydsl.core.types.Predicate;
import org.guiders.api.domain.Account;
import org.guiders.api.domain.QAccount;
import org.guiders.api.exception.AccountNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("dev")
class QueryDslTest {

    @Autowired
    private TestRepository testRepository;

    @Test
    @DisplayName("querydsl 테스트")
    void crud() {
        Predicate predicate = QAccount.account
                .username.first.containsIgnoreCase("sam")
                .and(QAccount.account.username.last.startsWith("kwon"));

        Account account = testRepository.findOne(predicate)
                .orElseThrow(AccountNotFoundException::new);

        assertThat(account).isNotNull();
    }

}
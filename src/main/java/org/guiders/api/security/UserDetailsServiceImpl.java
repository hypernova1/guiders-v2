package org.guiders.api.security;

import lombok.RequiredArgsConstructor;
import org.guiders.api.domain.Account;
import org.guiders.api.exception.AccountNotFoundException;
import org.guiders.api.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public UserPrincipal loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(AccountNotFoundException::new);

        account.updateLoginDate();

        return UserPrincipal.create(account);
    }
}

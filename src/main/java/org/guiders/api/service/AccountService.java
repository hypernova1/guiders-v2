package org.guiders.api.service;

import lombok.RequiredArgsConstructor;
import org.guiders.api.domain.Account;
import org.guiders.api.exception.AccountNotFoundException;
import org.guiders.api.payload.AccountDto;
import org.guiders.api.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;

    public AccountDto.InfoResponse get(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);
        return modelMapper.map(account, AccountDto.InfoResponse.class);


    }

    public List<AccountDto.InfoResponse> getList(PageRequest pageRequest) {

        Page<Account> accounts = this.accountRepository.findAll(pageRequest);

        return accounts.getContent().stream()
                .map(account -> modelMapper.map(account, AccountDto.InfoResponse.class))
                .collect(Collectors.toList());
    }

}

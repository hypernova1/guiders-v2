package org.guiders.api.controller;

import lombok.RequiredArgsConstructor;
import org.guiders.api.payload.AccountDto;
import org.guiders.api.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto.InfoResponse> getAccount(@PathVariable Long accountId) {
        AccountDto.InfoResponse accountDto = accountService.getAccount(accountId);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping
    public ResponseEntity<?> getAccountList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<AccountDto.InfoResponse> accountList = accountService.getAccountList(page, size);

        return ResponseEntity.ok(accountList);
    }

}

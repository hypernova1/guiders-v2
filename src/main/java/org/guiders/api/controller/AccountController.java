package org.guiders.api.controller;

import lombok.RequiredArgsConstructor;
import org.guiders.api.payload.AccountDto;
import org.guiders.api.service.AccountService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto.InfoResponse> getAccount(@PathVariable Long id) {
        AccountDto.InfoResponse accountDto = accountService.get(id);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping
    public ResponseEntity<?> getAccountList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("id").descending());
        List<AccountDto.InfoResponse> accountList = accountService
                .getList(pageRequest);

        return ResponseEntity.ok(accountList);
    }

}

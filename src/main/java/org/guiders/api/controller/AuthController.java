package org.guiders.api.controller;

import lombok.RequiredArgsConstructor;
import org.guiders.api.domain.Account;
import org.guiders.api.payload.AccountDto;
import org.guiders.api.payload.AuthDto;
import org.guiders.api.repository.AccountRepository;
import org.guiders.api.service.AccountService;
import org.guiders.api.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletContextListener;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AccountService accountService;

    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmail(@Valid @RequestBody AuthDto.EmailRequest request) {

        boolean isDuplicated = authService.isEmailDuplicated(request.getEmail());

        if (isDuplicated) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().build();
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody AuthDto.JoinRequest request) {

        Long accountId = authService.join(request);

        if (accountId == null) return ResponseEntity.badRequest().build();

        AccountDto.InfoResponse accountDto = accountService.get(accountId);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/account/{accountId}")
                .buildAndExpand(accountId)
                .toUri();

        return ResponseEntity.created(location).body(accountDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthDto.LoginRequest request) {

        boolean result = authService.isValid(request);

        if (!result) {
            return ResponseEntity.notFound().build();
        }

        AccountDto.InfoResponse loggedAccount = accountService.get(request.getEmail());

        return ResponseEntity.ok(loggedAccount);
    }

}

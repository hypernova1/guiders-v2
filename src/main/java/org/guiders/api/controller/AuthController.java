package org.guiders.api.controller;

import lombok.RequiredArgsConstructor;
import org.guiders.api.payload.AccountDto;
import org.guiders.api.payload.AuthDto;
import org.guiders.api.payload.JwtResponse;
import org.guiders.api.service.AccountService;
import org.guiders.api.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

        String jwt = authService.isValid(request);

        AccountDto.InfoResponse loggedAccount = accountService.get(request.getEmail());

        return ResponseEntity.ok(new JwtResponse(jwt, loggedAccount));
    }

    @GetMapping("/password/{email}")
    public ResponseEntity<?> password(@PathVariable String email) {

        boolean result = authService.sendPassword(email);

        if (!result) return ResponseEntity.notFound().build();

        return ResponseEntity.ok().build();
    }

}

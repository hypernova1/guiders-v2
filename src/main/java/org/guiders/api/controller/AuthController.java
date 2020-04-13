package org.guiders.api.controller;

import lombok.RequiredArgsConstructor;
import org.guiders.api.domain.Account;
import org.guiders.api.payload.AuthDto;
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

    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmail(@Valid @RequestBody AuthDto.EmailRequest request) {

        int result = authService.checkEmail(request);

        if (result > 0) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().build();
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody AuthDto.JoinRequest request) {

        Long accountId = authService.join(request);

        if (accountId == null) return ResponseEntity.badRequest().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{accountId}")
                .buildAndExpand(accountId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthDto.LoginRequest request) {

        AuthDto.LoginResponse loggedAccount = authService.login(request);

        return ResponseEntity.ok(loggedAccount);
    }

}

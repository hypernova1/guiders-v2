package org.guiders.api.service;

import lombok.RequiredArgsConstructor;
import org.guiders.api.constant.RoleName;
import org.guiders.api.domain.Account;
import org.guiders.api.domain.Follower;
import org.guiders.api.domain.Guider;
import org.guiders.api.domain.Role;
import org.guiders.api.payload.AuthDto;
import org.guiders.api.repository.AccountRepository;
import org.guiders.api.repository.FollowerRepository;
import org.guiders.api.repository.GuiderRepository;
import org.guiders.api.repository.RoleRepository;
import org.guiders.api.security.JwtUtils;
import org.guiders.api.util.GMailSender;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;
    private final GuiderRepository guiderRepository;
    private final FollowerRepository followerRepository;
    private final RoleRepository roleRepository;

    private final GMailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public String isValid(AuthDto.LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generationJwtToken(authentication);

        return jwt;
    }

    public Long join(AuthDto.JoinRequest request) {
        if (isEmailDuplicated(request.getEmail())) return null;

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        Account account;
        if (request.isGuider()) {

            Role role = roleRepository.findByName(RoleName.ROLE_GUIDER)
                    .orElseThrow(RuntimeException::new);
            Guider guider = request.toGuiderEntity();
            guider.getRoles().add(role);
            account = guiderRepository.save(guider);
            return account.getId();
        }

        Role role = roleRepository.findByName(RoleName.ROLE_FOLLOWER)
                .orElseThrow(RuntimeException::new);
        Follower follower = request.toFollowerEntity();
        follower.getRoles().add(role);
        account = followerRepository.save(follower);

        return account.getId();
    }

    public boolean isEmailDuplicated(String email) {
        return accountRepository.existsByEmail(email);
    }

    public boolean sendPassword(String email) {
        boolean isExist = accountRepository.existsByEmail(email);

        if (!isExist) return false;

        mailSender.sendPassword(email);

        return true;
    }
}

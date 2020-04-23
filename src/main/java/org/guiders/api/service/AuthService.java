package org.guiders.api.service;

import lombok.RequiredArgsConstructor;
import org.guiders.api.domain.Account;
import org.guiders.api.domain.Follower;
import org.guiders.api.domain.Guider;
import org.guiders.api.payload.AuthDto;
import org.guiders.api.repository.AccountRepository;
import org.guiders.api.repository.FollowerRepository;
import org.guiders.api.repository.GuiderRepository;
import org.guiders.api.util.GMailSender;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;
    private final GuiderRepository guiderRepository;
    private final FollowerRepository followerRepository;
    private final GMailSender mailSender;

    public boolean isValid(AuthDto.LoginRequest request) {
        return accountRepository
                .existsByEmailAndPassword(request.getEmail(), request.getPassword());
    }

    public Long join(AuthDto.JoinRequest request) {

        if (isEmailDuplicated(request.getEmail())) return null;

        Account account;
        if (request.isGuider()) {
            Guider guider = modelMapper.map(request, Guider.class);
            account = guiderRepository.save(guider);
            return account.getId();
        }

        Follower follower = modelMapper.map(request, Follower.class);
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

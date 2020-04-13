package org.guiders.api.service;

import lombok.RequiredArgsConstructor;
import org.guiders.api.domain.Account;
import org.guiders.api.domain.Follower;
import org.guiders.api.domain.Guider;
import org.guiders.api.exception.WrongAccountException;
import org.guiders.api.payload.AuthDto;
import org.guiders.api.repository.FollowerRepository;
import org.guiders.api.repository.GuiderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ModelMapper modelMapper;
    private final GuiderRepository guiderRepository;
    private final FollowerRepository followerRepository;

    public AuthDto.LoginResponse login(AuthDto.LoginRequest request) {

        Account account = null;
        if (request.getUserType().equals("guider")) {
            account = guiderRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
                    .orElseThrow(() -> new WrongAccountException());
        } else {
            account = followerRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
                    .orElseThrow(() -> new WrongAccountException());
        }

        AuthDto.LoginResponse result = modelMapper.map(account, AuthDto.LoginResponse.class);

        return result;
    }

    public Long join(AuthDto.@Valid JoinRequest request) {

        Account account = null;
        if (request.getUserType().equals("guider")) {
            if (guiderRepository.countByEmail(request.getEmail()) > 0) return null;
            Guider guider = modelMapper.map(request, Guider.class);
            account = guiderRepository.save(guider);
        } else {
            if (followerRepository.countByEmail(request.getEmail()) > 0) return null;
            Follower follower = modelMapper.map(request, Follower.class);
            account = followerRepository.save(follower);
        }

        return account.getId();
    }

    public int checkEmail(AuthDto.EmailRequest request) {

        int result = 0;
        if (request.getUserType().equals("guider")) {
            result = guiderRepository.countByEmail(request.getEmail());
        } else {
            result = followerRepository.countByEmail(request.getEmail());
        }

        return result;
    }
}

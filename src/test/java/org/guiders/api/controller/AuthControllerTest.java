package org.guiders.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.guiders.api.domain.Guider;
import org.guiders.api.payload.AuthDto;
import org.guiders.api.repository.GuiderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GuiderRepository guiderRepository;

    @BeforeEach
    void insertDB() {
        Guider guider = new Guider();
        guider.setEmail("chtlstjd01@naver.com");
        guider.setPassword("1111");
        guider.setUsername("melchor");
        guiderRepository.save(guider);
    }

    @Test
    void join() throws Exception {

        AuthDto.JoinRequest requestDto = new AuthDto.JoinRequest();
        requestDto.setEmail("hypemova@gmail.com");
        requestDto.setPassword("1111");
        requestDto.setUserType("guider");
        requestDto.setUsername("sam");
        String json = new ObjectMapper().writeValueAsString(requestDto);

        mockMvc.perform(post("/auth/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void login() throws Exception {

        AuthDto.LoginRequest requestDto = new AuthDto.LoginRequest();
        requestDto.setEmail("chtlstjd01@naver.com");
        requestDto.setPassword("1111");
        requestDto.setUserType("guider");
        String json = new ObjectMapper().writeValueAsString(requestDto);

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
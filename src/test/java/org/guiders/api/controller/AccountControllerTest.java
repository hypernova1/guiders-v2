package org.guiders.api.controller;

import org.guiders.api.domain.Guider;
import org.guiders.api.repository.GuiderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GuiderRepository guiderRepository;

    private Guider savedGuider;

    @BeforeEach
    void insertDB() {
        Guider guider = Guider.builder()
                .email("chtlstjd01@naver.com")
                .firstName("sam")
                .lastName("last")
                .password("1111")
                .build();
        savedGuider = guiderRepository.save(guider);
    }

    @Test
    @DisplayName("계정 정보를 가져온다.")
    void getAccount() throws Exception {
        mockMvc.perform(get("/account/" + savedGuider.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("계정 리스트를 가져온다.")
    void getAccountList() throws Exception {
        mockMvc.perform(get("/account"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

}
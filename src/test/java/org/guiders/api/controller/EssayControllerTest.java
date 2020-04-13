package org.guiders.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.guiders.api.domain.Essay;
import org.guiders.api.domain.Guider;
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
class EssayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GuiderRepository guiderRepository;

    private Guider guider;

    @BeforeEach
    void insertDB() {
        Guider guider = Guider.builder()
                .email("chtlstjd01@naver.com")
                .password("1111")
                .username("melchor")
                .build();
        guider = guiderRepository.save(guider);
    }

    @Test
    void register() throws Exception {

        Essay essay = Essay.builder()
                .title("title")
                .content("content")
                .build();
        String json = new ObjectMapper().writeValueAsString(essay);

        mockMvc.perform(post("/essay")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());
    }

}
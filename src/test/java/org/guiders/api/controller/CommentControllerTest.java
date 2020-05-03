package org.guiders.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.guiders.api.domain.Essay;
import org.guiders.api.domain.Guider;
import org.guiders.api.payload.CommentDto;
import org.guiders.api.repository.CommentRepository;
import org.guiders.api.repository.EssayRepository;
import org.guiders.api.repository.GuiderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private EssayRepository essayRepository;
    @Autowired
    private GuiderRepository guiderRepository;

    private Essay savedEssay;

    @BeforeEach
    void insertDB() {
        Guider guider = guiderRepository.findAll().get(0);

        Essay essay = Essay.builder()
                .title("title")
                .content("content")
                .guider(guider)
                .build();

        savedEssay = essayRepository.save(essay);
    }

    @Test
    void register() throws Exception {

        CommentDto.Request request = new CommentDto.Request();
        request.setContent("content");
        request.setEssayId(savedEssay.getId());

        String json = new ObjectMapper().writeValueAsString(request);
        mockMvc.perform(post("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
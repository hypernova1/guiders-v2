package org.guiders.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.guiders.api.domain.Follower;
import org.guiders.api.domain.Guider;
import org.guiders.api.domain.Question;
import org.guiders.api.exception.AccountNotFoundException;
import org.guiders.api.payload.AnswerDto;
import org.guiders.api.repository.FollowerRepository;
import org.guiders.api.repository.GuiderRepository;
import org.guiders.api.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FollowerRepository followerRepository;
    @Autowired
    private GuiderRepository guiderRepository;
    @Autowired
    private QuestionRepository questionRepository;

    private Question savedQuestion;

    @BeforeEach
    void insertDB() {

        Follower follower = followerRepository.findById(2L)
                .orElseThrow(AccountNotFoundException::new);
        Guider guider = guiderRepository.findById(1L)
                .orElseThrow(AccountNotFoundException::new);

        Question question = Question.builder()
                .title("question")
                .content("content")
                .writer(follower)
                .build();

        savedQuestion = questionRepository.save(question);
    }

    @Test
    void resister() throws Exception {

        AnswerDto.RegisterRequest request = new AnswerDto.RegisterRequest();
        request.setContent("answer content");
        request.setQuestionId(savedQuestion.getId());

        String json = new ObjectMapper().writeValueAsString(request);

        mockMvc.perform(post("/answer").contentType(MediaType
                .APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {

        resister();

        AnswerDto.UpdateRequest request = new AnswerDto.UpdateRequest();
        String content = "update content";
        request.setContent(content);

        String json = new ObjectMapper().writeValueAsString(request);

        mockMvc.perform(put("/answer/4")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.content", is(content)));


    }

}
package org.guiders.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.guiders.api.domain.Answer;
import org.guiders.api.domain.Follower;
import org.guiders.api.domain.Guider;
import org.guiders.api.domain.Question;
import org.guiders.api.exception.AccountNotFoundException;
import org.guiders.api.payload.QuestionDto;
import org.guiders.api.repository.AnswerRepository;
import org.guiders.api.repository.FollowerRepository;
import org.guiders.api.repository.GuiderRepository;
import org.guiders.api.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private FollowerRepository followerRepository;
    @Autowired
    private GuiderRepository guiderRepository;

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

        Answer answer = Answer.builder()
                .content("content")
                .title("title")
                .question(question)
                .guider(guider)
                .build();
//        savedQuestion.setAnswer(answer);
//        answerRepository.save(answer);
    }

    @Test
    void getList() throws Exception {
        mockMvc.perform(get("/question"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getDetail() throws Exception {
        mockMvc.perform(get("/question/" + savedQuestion.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void register() throws Exception {
        QuestionDto.Request request = new QuestionDto.Request();
        request.setTitle("question 입니다");
        request.setContent("내용입니다.");
        request.setGuiderId(1L);

        String json = new ObjectMapper().writeValueAsString(request);

        mockMvc.perform(post("/question")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        QuestionDto.UpdateRequest request = new QuestionDto.UpdateRequest();
        request.setTitle("question 입니다");
        request.setContent("내용입니다.");

        String json = new ObjectMapper().writeValueAsString(request);

        mockMvc.perform(put("/question/" + 3)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
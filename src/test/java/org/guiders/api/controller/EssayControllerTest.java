package org.guiders.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.guiders.api.domain.Essay;
import org.guiders.api.payload.EssayDto;
import org.guiders.api.repository.EssayRepository;
import org.guiders.api.repository.GuiderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class EssayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EssayRepository essayRepository;
    @Autowired
    private GuiderRepository guiderRepository;

    private Essay savedEssay;

    @BeforeEach
    void insertDB() {
        Essay essay = Essay.builder()
                .guider(guiderRepository.findAll().get(0))
                .title("title")
                .content("content")
                .build();

        savedEssay = essayRepository.save(essay);
    }

    @Test
    @DisplayName("에세이를 등록한다.")
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

    @Test
    @DisplayName("에세이를 수정한다.")
    void update() throws Exception {

        EssayDto.UpdateRequest essayDto = new EssayDto.UpdateRequest();
        String title = "update title";
        String content = "update content";
        essayDto.setTitle(title);
        essayDto.setContent(content);

        String json = new ObjectMapper().writeValueAsString(essayDto);

        mockMvc.perform(put("/essay/" + savedEssay.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.title", is(title)))
                .andExpect(jsonPath("$.content", is(content)));
    }

    @Test
    @DisplayName("에세이 리스트를 가져온다.")
    void getList() throws Exception {
        mockMvc.perform(get("/essay"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DisplayName("에세이 내용을 본다.")
    void getDetail() throws Exception {
        mockMvc.perform(get("/essay/" + savedEssay.getId()))
                .andDo(print())
                .andExpect(jsonPath("$.title", is(savedEssay.getTitle())))
                .andExpect(jsonPath("$.content", is(savedEssay.getContent())));
    }


}
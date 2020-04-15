package org.guiders.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.guiders.api.domain.Guider;
import org.guiders.api.payload.GuiderDto;
import org.guiders.api.repository.GuiderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GuiderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GuiderRepository guiderRepository;

    private Guider savedGuider;

    @BeforeEach
    void insertDB() {
        Guider guider = Guider.builder()
                .email("chtlstjd01@gmail.com")
                .username("sam")
                .password("1111")
                .build();
        savedGuider = guiderRepository.save(guider);
    }

    @Test
    void getDetail() throws Exception {

        mockMvc.perform(get("/guider/" + savedGuider.getId()))
                .andDo(print())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void getList() throws Exception {
        mockMvc.perform(get("/guider"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void update() throws Exception {

        GuiderDto.DetailRequest request = new GuiderDto.DetailRequest();
        request.setUsername("sam cham");
        request.setPassword("3333");
        String json = new ObjectMapper().writeValueAsString(request);

        mockMvc.perform(put("/guider/" + savedGuider.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void delete_() throws Exception {
        mockMvc.perform(delete("/guider/" + savedGuider.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
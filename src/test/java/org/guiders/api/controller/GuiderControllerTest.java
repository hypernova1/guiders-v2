package org.guiders.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.guiders.api.domain.Guider;
import org.guiders.api.model.Name;
import org.guiders.api.payload.GuiderDto;
import org.guiders.api.repository.GuiderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
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
@ActiveProfiles("dev")
class GuiderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GuiderRepository guiderRepository;

    private Guider savedGuider;

    @BeforeEach
    void insertDB() {
        Guider guider = Guider.builder()
                .email("hypernova12@naver.com")
                .firstName("sam")
                .lastName("lasttt")
                .password("1111")
                .build();
        savedGuider = guiderRepository.save(guider);
    }

    @Test
    @WithMockUser("test@test.com")
    @DisplayName("가이더의 정보를 가져온다.")
    void getDetail() throws Exception {

        mockMvc.perform(get("/guider/" + savedGuider.getId()))
                .andDo(print())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    @DisplayName("가이더 목록을 가져온다.")
    void getList() throws Exception {
        mockMvc.perform(get("/guider"))
                .andDo(print())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @DisplayName("가이더의 정보를 변경한다.")
    void update() throws Exception {

        GuiderDto.DetailRequest request = new GuiderDto.DetailRequest();
        request.setUsername(new Name("samch", "park"));
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
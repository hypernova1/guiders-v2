package org.guiders.api.repository;

import org.guiders.api.domain.Guider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuiderRepositoryTest {

    @Autowired
    private GuiderRepository guiderRepository;

    @Test
    public void insert() {
        Guider guider = Guider.builder()
                .email("hypemova@gmail.com")
                .password("1111")
                .username("sam")
                .build();

        Guider savedGuider = guiderRepository.save(guider);

        assertNotNull(savedGuider);
    }

}
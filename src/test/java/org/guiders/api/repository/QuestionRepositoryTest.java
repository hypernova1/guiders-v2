package org.guiders.api.repository;

import org.aspectj.lang.annotation.Before;
import org.guiders.api.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    private Question savedQuestion;

    @BeforeEach
    void insertDB() {
        Question question = Question.builder()
                .title("quququ")
                .content("cococo")
                .build();

        savedQuestion = questionRepository.save(question);
    }

    @Test
    @Transactional
    void softDelete() {
        questionRepository.softDelete(savedQuestion.getId());
    }

}
package org.guiders.api.repository;

import org.aspectj.lang.annotation.Before;
import org.guiders.api.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    private Question savedQuestion;

    @BeforeEach
    @Transactional
    void insertDB() {
        Question question = Question.builder()
                .title("quququ")
                .content("cococo")
                .build();

        Question question2 = Question.builder()
                .title("quququ")
                .content("cococo")
                .build();

        List<Question> questions = questionRepository.saveAll(Arrays.asList(question, question2));
        savedQuestion = questions.get(0);

        questionRepository.softDelete(questions.get(1).getId());
    }

    @Test
    @Transactional
    void softDelete() {
        questionRepository.softDelete(savedQuestion.getId());
    }

    @Test
    @Transactional
    void findAll() {
        PageRequest of = PageRequest.of(0, 10);
        questionRepository.findAll(of).forEach(System.out::println);
    }

}
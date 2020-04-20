package org.guiders.api.service;

import lombok.RequiredArgsConstructor;
import org.guiders.api.domain.Answer;
import org.guiders.api.domain.Question;
import org.guiders.api.exception.PostNotFoundException;
import org.guiders.api.payload.AnswerDto;
import org.guiders.api.repository.AnswerRepository;
import org.guiders.api.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final ModelMapper modelMapper;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public Long resister(AnswerDto.RegisterRequest request) {

        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(PostNotFoundException::new);

        Answer answer = modelMapper.map(request, Answer.class);
        answer.setQuestion(question);

        Answer savedAnswer = answerRepository.save(answer);

        return savedAnswer.getId();
    }

    @Transactional
    public void update(Long id, AnswerDto.UpdateRequest request) {

        Answer answer = answerRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        answer.update(request);
    }

    public AnswerDto.Response getDetail(Long id) {

        Answer answer = answerRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        return modelMapper.map(answer, AnswerDto.Response.class);
    }

    @Transactional
    public void delete(Long id) {

        answerRepository.softDelete(id);

    }
}

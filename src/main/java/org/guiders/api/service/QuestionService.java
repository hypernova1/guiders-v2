package org.guiders.api.service;

import lombok.RequiredArgsConstructor;
import org.guiders.api.domain.Answer;
import org.guiders.api.domain.Guider;
import org.guiders.api.domain.Question;
import org.guiders.api.exception.AccountNotFoundException;
import org.guiders.api.exception.PostNotFoundException;
import org.guiders.api.payload.AnswerDto;
import org.guiders.api.payload.FollowerDto;
import org.guiders.api.payload.GuiderDto;
import org.guiders.api.payload.QuestionDto;
import org.guiders.api.repository.GuiderRepository;
import org.guiders.api.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final ModelMapper modelMapper;
    private final QuestionRepository questionRepository;
    private final GuiderRepository guiderRepository;

    public List<QuestionDto.Response> getList(PageRequest pageRequest) {

        Page<Question> questions = this.questionRepository.findAll(pageRequest);

        return questions.getContent()
                .stream()
                .map(q -> {
                    QuestionDto.Response questionDto = modelMapper.map(q, QuestionDto.Response.class);
                    questionDto.setWriter(modelMapper.map(q.getWriter(), FollowerDto.Response.class));
                    return questionDto;
                }).collect(Collectors.toList());
    }

    public QuestionDto.DetailResponse getDetail(Long id) {

        Question question = questionRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        QuestionDto.DetailResponse questionDto = modelMapper.map(question, QuestionDto.DetailResponse.class);

        if (question.answerNotEmpty()) {
            Answer answer = question.getAnswer();
            Guider guider = answer.getWriter();
            AnswerDto.Response answerDto = modelMapper.map(answer, AnswerDto.Response.class);
            GuiderDto.DetailResponse guiderDto = modelMapper.map(guider, GuiderDto.DetailResponse.class);

            answerDto.setGuider(guiderDto);
            questionDto.setAnswer(answerDto);
        }

        return questionDto;
    }

    public Long register(QuestionDto.Request request) {

        Guider guider = guiderRepository.findById(request.getGuiderId())
                .orElseThrow(AccountNotFoundException::new);

        Question question = Question.toEntity(request, guider);
        Question savedQuestion = questionRepository.save(question);

        return savedQuestion.getId();
    }

    @Transactional
    public boolean update(QuestionDto.UpdateRequest request, Long id) {

        Question question = questionRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        if (question.answerNotEmpty()) return false;

        question.update(request);

        return true;
    }

    @Transactional
    public void delete(Long id) {
        questionRepository.softDelete(id);
    }
}

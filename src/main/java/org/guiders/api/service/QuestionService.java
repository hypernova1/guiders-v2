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
import org.modelmapper.config.Configuration;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final ModelMapper modelMapper;
    private final QuestionRepository questionRepository;
    private final GuiderRepository guiderRepository;

    public List<QuestionDto.Response> getList(int page, int size) {

        PageRequest pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());

        Page<Question> questions = questionRepository.findAll(pageable);

        List<QuestionDto.Response> questionList = questions.getContent().stream()
                .map(q -> modelMapper.map(q, QuestionDto.Response.class))
                .collect(Collectors.toList());

        questionList.forEach(q -> q.setFollower(modelMapper.map(q.getFollower(), FollowerDto.Response.class)));

        return questionList;

    }

    public QuestionDto.DetailResponse getDetail(Long id) {

        Question question = questionRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        QuestionDto.DetailResponse questionDto = modelMapper.map(question, QuestionDto.DetailResponse.class);

        if (question.getAnswer() != null) {
            Answer answer = question.getAnswer();
            Guider guider = answer.getGuider();
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

        Question question = modelMapper.map(request, Question.class);
        question.setGuider(guider);

        Question savedQuestion = questionRepository.save(question);

        return savedQuestion.getId();
    }

    @Transactional
    public Long update(@Valid QuestionDto.UpdateRequest request, Long id) {

        Question question = questionRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        if (question.getAnswer() != null) return null;

        question.update(request);

        return question.getId();
    }
}

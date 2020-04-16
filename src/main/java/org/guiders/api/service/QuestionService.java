package org.guiders.api.service;

import lombok.RequiredArgsConstructor;
import org.guiders.api.domain.Question;
import org.guiders.api.payload.FollowerDto;
import org.guiders.api.payload.QuestionDto;
import org.guiders.api.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final ModelMapper modelMapper;
    private final QuestionRepository questionRepository;

    public List<QuestionDto.Response> getList(int page, int size) {

        PageRequest pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());

        Page<Question> questions = questionRepository.findAll(pageable);

        List<QuestionDto.Response> questionList = questions.getContent().stream()
                .map(q -> modelMapper.map(q, QuestionDto.Response.class))
                .collect(Collectors.toList());

        questionList.forEach(q -> q.setFollower(modelMapper.map(q.getFollower(), FollowerDto.Response.class)));

        return questionList;

    }
}

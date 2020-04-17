package org.guiders.api.controller;

import lombok.RequiredArgsConstructor;
import org.guiders.api.payload.QuestionDto;
import org.guiders.api.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<?> getQuestionList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<QuestionDto.Response> questionList = questionService.getList(page, size);

        return ResponseEntity.ok(questionList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable Long id) {

        QuestionDto.DetailResponse detail = questionService.getDetail(id);

        return ResponseEntity.ok(detail);
    }

}

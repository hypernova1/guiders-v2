package org.guiders.api.controller;

import lombok.RequiredArgsConstructor;
import org.guiders.api.payload.QuestionDto;
import org.guiders.api.service.QuestionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<?> getList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<QuestionDto.Response> questionList = questionService
                .getList(PageRequest.of(page - 1, size, Sort.by("id").descending()));

        return ResponseEntity.ok(questionList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {

        QuestionDto.DetailResponse detail = questionService.getDetail(id);

        return ResponseEntity.ok(detail);
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody QuestionDto.Request request) {

        Long questionId = questionService.register(request);
        QuestionDto.DetailResponse createdQuestion = questionService.getDetail(questionId);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(questionId)
                .toUri();

        return ResponseEntity.created(location).body(createdQuestion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody QuestionDto.UpdateRequest request) {

        boolean isUpdate = questionService.update(request, id);

        if (!isUpdate) return ResponseEntity.badRequest().build();

        QuestionDto.DetailResponse questionDto = questionService.getDetail(id);

        return ResponseEntity.ok(questionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        questionService.delete(id);

        return ResponseEntity.ok().build();
    }


}

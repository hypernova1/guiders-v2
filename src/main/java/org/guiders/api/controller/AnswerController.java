package org.guiders.api.controller;

import lombok.RequiredArgsConstructor;
import org.guiders.api.payload.AnswerDto;
import org.guiders.api.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<?> resisterAnswer(@RequestBody @Valid AnswerDto.RegisterRequest request) {

        Long answerId = answerService.resister(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(answerId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody @Valid AnswerDto.UpdateRequest request) {

       answerService.update(id, request);

       AnswerDto.Response answerDto = answerService.getDetail(id);

       return ResponseEntity.ok(answerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        answerService.delete(id);

        return ResponseEntity.ok().build();
    }


}

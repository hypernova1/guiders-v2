package org.guiders.api.controller;

import lombok.RequiredArgsConstructor;
import org.guiders.api.payload.CommentDto;
import org.guiders.api.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody CommentDto.Request request) {

        Long commentId = commentService.register(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(commentId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody CommentDto.Request request) {

        commentService.update(id, request);
        CommentDto.Response commentDto = commentService.get(id);

        return ResponseEntity.ok(commentDto);
    }

}

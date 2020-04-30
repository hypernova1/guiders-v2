package org.guiders.api.controller;

import lombok.RequiredArgsConstructor;
import org.guiders.api.payload.EssayDto;
import org.guiders.api.service.EssayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/essay")
@RequiredArgsConstructor
public class EssayController {

    private final EssayService essayService;


    @PostMapping
    public ResponseEntity<?> resister(@Valid @RequestBody EssayDto.ResisterRequest request) {

        Long essayId = essayService.register(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(essayId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetail() {

        return null;
    }

}

package org.guiders.api.controller;

import lombok.RequiredArgsConstructor;
import org.guiders.api.payload.EssayDto;
import org.guiders.api.service.EssayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        EssayDto.ResponseDetail essay = essayService.getDetail(id);
        return ResponseEntity.ok(essay);
    }

    @GetMapping
    public ResponseEntity<?> getList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<EssayDto.Response> essayList = essayService.getList(page, size);

        return ResponseEntity.ok(essayList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody EssayDto.UpdateRequest request) {

        essayService.update(id, request);
        EssayDto.ResponseDetail essayDto = essayService.getDetail(id);

        return ResponseEntity.ok(essayDto);
    }

}

package org.guiders.api.controller;

import lombok.RequiredArgsConstructor;
import org.guiders.api.payload.AccountDto;
import org.guiders.api.payload.GuiderDto;
import org.guiders.api.service.GuiderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guider")
@RequiredArgsConstructor
public class GuiderController {

    private final GuiderService guiderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getGuider(@PathVariable Long id) {

        GuiderDto.DetailResponse guiderDto = guiderService.getDetail(id);

        return ResponseEntity.ok(guiderDto);
    }

    @GetMapping
    public ResponseEntity<?> getGuiderList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<GuiderDto.DetailResponse> guiders = guiderService.getList(page, size);

        return ResponseEntity.ok(guiders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGuider(
            @PathVariable Long id,
            @RequestBody GuiderDto.DetailRequest request) {

        guiderService.update(id, request);
        GuiderDto.DetailResponse guiderDto = guiderService.getDetail(id);

        return ResponseEntity.ok(guiderDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGuider(@PathVariable Long id) {
        guiderService.delete(id);

        return ResponseEntity.ok().build();
    }

}

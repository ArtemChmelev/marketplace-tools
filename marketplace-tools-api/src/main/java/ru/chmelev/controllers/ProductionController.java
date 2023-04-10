package ru.chmelev.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.chmelev.dto.production.request.ProductionRequestDto;


public interface ProductionController {

    @PostMapping("/production")
    ResponseEntity<?> create (@RequestBody @Validated ProductionRequestDto productionRequestDto);

    @GetMapping("/production/{productionID}")
    ResponseEntity<?> findById(@PathVariable("productionID") long productionId);

    @DeleteMapping("/production/{productionId}")
    ResponseEntity<?> delete (@PathVariable("productionId") long productionId);

    @PutMapping("/production/{productionId}")
    ResponseEntity<?> update (@PathVariable("productionId") Long productionId, @RequestBody @Validated ProductionRequestDto productionRequestDto);

}

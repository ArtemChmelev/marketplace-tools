package ru.chmelev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.chmelev.dto.marketplace.MarketplaceRequestDto;
import ru.chmelev.dto.marketplace.UpdateContactsDto;
import ru.chmelev.dto.marketplace.response.MarketplaceResponseDto;
public interface MarketplaceController {

    @PostMapping("/marketplace")
    ResponseEntity<?> create(@RequestBody @Validated MarketplaceRequestDto marketplaceRequestDto);
    @GetMapping("/marketplace/{id}")
    ResponseEntity<MarketplaceResponseDto> findById(@PathVariable("id") Long id);
    @GetMapping("/marketplace/byName/{name}")
    ResponseEntity<?>findByName(@PathVariable("name")String name);
    @PutMapping("/marketplace/{id}")
    ResponseEntity<?>update(@PathVariable("id")Long id,@RequestBody @Validated MarketplaceRequestDto marketplaceRequestDto);
    @GetMapping("/marketplace")
    ResponseEntity<?>findAll(@RequestParam(required = false,defaultValue = "")String name);
    @DeleteMapping("/marketplace/{id}")
    ResponseEntity<?>deleteById(@PathVariable("id")Long id);
    @PatchMapping("/marketplace/{id}")
    ResponseEntity<?>updateContacts(@PathVariable("id")Long id, @RequestBody @Validated UpdateContactsDto updateContactsDto);
}


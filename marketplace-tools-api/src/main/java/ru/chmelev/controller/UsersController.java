package ru.chmelev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.chmelev.dto.users.UsersRequestDto;


public interface UsersController {


    @PostMapping("/users")
    ResponseEntity<?> create(@RequestBody @Validated UsersRequestDto usersRequestDto);

    @GetMapping("/users")
    ResponseEntity<?>findAll(@RequestParam(required = false,defaultValue = "")String filter);

    @GetMapping("/users/{id}")
    ResponseEntity<?> findById(@PathVariable("id") Long id);

    @GetMapping("/users/byName/{userName}")
    ResponseEntity<?>findByUserName(@PathVariable("userName")String userName);

    @PutMapping("/users/{id}")
    ResponseEntity<?>update(@PathVariable("id")Long id,@RequestBody @Validated UsersRequestDto usersRequestDto);


    @DeleteMapping("/users/{id}")
    ResponseEntity<?>deleteById(@PathVariable("id")Long id);

    @PatchMapping("/users/{user_id}/add-marketplace/{marketplace_id}")
    ResponseEntity<?>addMarketplace(@PathVariable("user_id")Long userId,@PathVariable("marketplace_id") Long marketplaceId);





}

package ru.chmelev.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.chmelev.dto.product.request.ProductRequestDto;

public interface ProductController {

    @PostMapping("/product")
    ResponseEntity<?>create(@RequestBody @Validated ProductRequestDto productRequestDto);

    @GetMapping("/product")
    ResponseEntity<?>findAll(@RequestParam(required = false,defaultValue = "")String filter);

    @GetMapping("/product/{productId}")
    ResponseEntity<?>findById(@PathVariable("productId")Long productId);

    @DeleteMapping("/product/{productId}")
    ResponseEntity<?>deleteById(@PathVariable("productId")Long productID);

    @GetMapping("/product/byName/{nameProduct}")
    ResponseEntity<?>findByNameProduct(@PathVariable("nameProduct")String nameProduct);
}

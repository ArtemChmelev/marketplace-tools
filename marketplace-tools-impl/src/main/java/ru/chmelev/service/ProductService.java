package ru.chmelev.service;

import ru.chmelev.dto.product.request.ProductRequestDto;
import ru.chmelev.dto.product.response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    ProductResponseDto readById(Long productId);

    void deleteById(Long productId);

    List<ProductResponseDto> readAll(String filter);

    ProductResponseDto readByNameProduct(String nameProduct);
}

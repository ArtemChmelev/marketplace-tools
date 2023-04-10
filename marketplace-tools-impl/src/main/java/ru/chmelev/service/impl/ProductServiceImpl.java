package ru.chmelev.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.chmelev.dto.product.request.ProductRequestDto;
import ru.chmelev.dto.product.response.ProductResponseDto;
import ru.chmelev.entity.Product;
import ru.chmelev.exception.NoFoundException;
import ru.chmelev.repositoriy.ProductRepositories;
import ru.chmelev.service.ProductService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepositories productRepositories;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepositories productRepositories, ModelMapper modelMapper) {
        this.productRepositories = productRepositories;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product forSave = modelMapper.map(productRequestDto, Product.class);
        Product save = productRepositories.save(forSave);

        return modelMapper.map(save, ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto readById(Long productId) {
        Optional<Product> byId = productRepositories.findById(productId);
        Product product = byId
                .orElseThrow(() -> new NoFoundException(String.format("Продукт не найден по данному product id %d", productId)));
        return modelMapper.map(product, ProductResponseDto.class);
    }

    @Override
    public void deleteById(Long productId) {
        productRepositories.findById(productId)
                .orElseThrow(() -> new NoFoundException(String.format("Продукт не найден по данному product id %d", productId)));
        productRepositories.deleteById(productId);

    }

    @Override
    public List<ProductResponseDto> readAll(String filter) {
        List<Product> all;
        if (Objects.equals(filter, "")) {
            all = productRepositories.findAll();
        } else {
            String postgresLike = "%%%s%%".formatted(filter);
            all = productRepositories.findAllByNameProductLikeIgnoreCase(postgresLike);


        }
        return all.stream()
                .map(i -> modelMapper.map(i, ProductResponseDto.class))
                .toList();
    }

    @Override
    public ProductResponseDto readByNameProduct(String nameProduct) {
        Product product = productRepositories.findByNameProduct(nameProduct)
                .orElseThrow(() -> new NoFoundException(String.format("Продукт не найден по данному имени %s", nameProduct)));
        return modelMapper.map(product, ProductResponseDto.class);
    }
}

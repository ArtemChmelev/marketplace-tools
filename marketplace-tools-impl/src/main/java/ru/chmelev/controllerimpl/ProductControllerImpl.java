package ru.chmelev.controllerimpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.chmelev.controllers.ProductController;
import ru.chmelev.dto.product.request.ProductRequestDto;
import ru.chmelev.dto.product.response.ProductResponseDto;
import ru.chmelev.service.ProductService;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class ProductControllerImpl implements ProductController {

    private ProductService productService;


    @Override
    public ResponseEntity<?> create(ProductRequestDto productRequestDto) {
        log.info("Request for created product");
        ProductResponseDto productResponseDto = productService.createProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDto);
    }

    @Override
    public ResponseEntity<?> findAll(String filter) {
        log.info("Request find all with name filter:{}", filter);
        List<ProductResponseDto> products = productService.readAll(filter);
        return ResponseEntity.ok(products);

    }

    @Override
    public ResponseEntity<?> findById(Long productId) {
        log.info("Request find by id:{}", productId);
        ProductResponseDto productResponseDto = productService.readById(productId);
        return ResponseEntity.ok(productResponseDto);
    }

    @Override
    public ResponseEntity<?> deleteById(Long productId) {
        log.info("Request for delete by id:{}", productId);
        productService.deleteById(productId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> findByNameProduct(String nameProduct) {
        log.info("Request for find by name product:{}", nameProduct);
        ProductResponseDto productResponseDto = productService.readByNameProduct(nameProduct);
        return ResponseEntity.ok(productResponseDto);
    }
}

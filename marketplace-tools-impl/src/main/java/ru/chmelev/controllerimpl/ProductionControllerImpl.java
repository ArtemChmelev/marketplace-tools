package ru.chmelev.controllerimpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.chmelev.controllers.ProductionController;
import ru.chmelev.dto.production.request.ProductionRequestDto;
import ru.chmelev.dto.production.response.ProductionResponseDto;
import ru.chmelev.service.ProductionService;

@RestController
@Slf4j
@AllArgsConstructor
public class ProductionControllerImpl implements ProductionController {

    ProductionService productionService;

    @Override
    public ResponseEntity<?> create(ProductionRequestDto productionRequestDto) {
        log.info("Request for saving production{}", productionRequestDto);
        ProductionResponseDto productResponseDto = productionService.create(productionRequestDto);
        return ResponseEntity.ok(productResponseDto);
    }

    @Override
    public ResponseEntity<?> findById(long productionId) {
        log.info("Request for search by id {}", productionId);
        ProductionResponseDto productResponseDto = productionService.readById(productionId);
        return ResponseEntity.ok(productResponseDto);
    }

    @Override
    public ResponseEntity<?> delete(long productionId) {
        log.info("Request for delete by id {}", productionId);
        productionService.deleteById(productionId);
        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<?> update(Long productionId, ProductionRequestDto productionRequestDto) {
        log.info("Request for update {}", productionRequestDto);
        ProductionResponseDto productResponseDto = productionService.update(productionId, productionRequestDto);
        return ResponseEntity.ok(productResponseDto);
    }
}

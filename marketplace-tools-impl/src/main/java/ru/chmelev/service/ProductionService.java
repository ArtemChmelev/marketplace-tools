package ru.chmelev.service;

import ru.chmelev.dto.production.request.ProductionRequestDto;
import ru.chmelev.dto.production.response.ProductionResponseDto;

public interface ProductionService {

    ProductionResponseDto create(ProductionRequestDto productionRequestDto);

    ProductionResponseDto readById(long productionId);

    void deleteById(long productionId);

    ProductionResponseDto update(Long id, ProductionRequestDto productionRequestDto);

}

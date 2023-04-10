package ru.chmelev.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.chmelev.dto.production.request.ProductionRequestDto;
import ru.chmelev.dto.production.response.ProductionResponseDto;
import ru.chmelev.entity.Production;
import ru.chmelev.exception.NoFoundException;
import ru.chmelev.repositoriy.ProductionRepositories;
import ru.chmelev.service.ProductionService;
import ru.chmelev.service.external.CalculateService;

import java.util.Optional;

@Service
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepositories productionRepositories;
    private final ModelMapper modelMapper;
    private final CalculateService calculateService;

    public ProductionServiceImpl(ProductionRepositories productionRepositories, ModelMapper modelMapper,
                                 CalculateService calculateService) {
        this.productionRepositories = productionRepositories;
        this.modelMapper = modelMapper;
        this.calculateService = calculateService;
    }

    @Override
    public ProductionResponseDto create(ProductionRequestDto productionRequestDto) {

        if (productionRequestDto.getOverallCost() != null && productionRequestDto.getProfitPercentage() != null) {
            return createProductionResponse(calculateService.calculateFinalOverall(productionRequestDto));

        } else if (productionRequestDto.getOverallCost() != null && productionRequestDto.getFinalPrice() != null) {
            return createProductionResponse(calculateService.calculateProfitOverall(productionRequestDto));

        } else if (productionRequestDto.getCost() != null && productionRequestDto.getProfitPercentage() != null) {
            return createProductionResponse(calculateService.calculateFinalForOne(productionRequestDto));

        } else if (productionRequestDto.getCost() != null && productionRequestDto.getFinalPrice() != null) {
            return createProductionResponse(calculateService.calculateProfitForOne(productionRequestDto));
        }
        return null;
    }

    private ProductionResponseDto createProductionResponse(ProductionResponseDto forSave) {
        Production production = modelMapper.map(forSave, Production.class);
        Production saved = productionRepositories.save(production);
        return modelMapper.map(saved, ProductionResponseDto.class);

    }

    @Override
    public ProductionResponseDto readById(long productionId) {
        Optional<Production> byId = productionRepositories.findById(productionId);
        Production found = byId
                .orElseThrow(() -> new NoFoundException(String.format("Продукция не найдена по данному id:{}", productionId)));
        return modelMapper.map(found, ProductionResponseDto.class);
    }

    @Override
    public void deleteById(long productionId) {
        productionRepositories.findById(productionId)
                .orElseThrow(() -> new NoFoundException(String.format("Маркетплейс не найден по данному id %d", productionId)));
        productionRepositories.deleteById(productionId);
    }

    @Override
    public ProductionResponseDto update(Long productionId, ProductionRequestDto productionRequestDto) {
        Optional<Production> optionalProduction = productionRepositories.findById(productionId);
        Production production = optionalProduction.orElseThrow(() -> new NoFoundException(
                String.format("Продукция не найдена по данному id: %d", productionId)));

        production.setCost(productionRequestDto.getCost());
        production.setOverallCost(productionRequestDto.getOverallCost());
        production.setProfitPercentage(productionRequestDto.getProfitPercentage());
        production.setFinalPrice(productionRequestDto.getFinalPrice());
        ProductionRequestDto map = modelMapper.map(production, ProductionRequestDto.class);

        ProductionResponseDto saved = null;

        if (productionRequestDto.getOverallCost() != null && productionRequestDto.getProfitPercentage() != null) {
            saved = createProductionResponse(calculateService.calculateFinalOverall(map));

        } else if (productionRequestDto.getOverallCost() != null && productionRequestDto.getFinalPrice() != null) {
            saved = createProductionResponse(calculateService.calculateProfitOverall(map));

        } else if (productionRequestDto.getCost() != null && productionRequestDto.getProfitPercentage() != null) {
            saved = createProductionResponse(calculateService.calculateProfitForOne(map));

        } else if (productionRequestDto.getCost() != null && productionRequestDto.getFinalPrice() != null) {
            saved = createProductionResponse(calculateService.calculateFinalForOne(map));
        }


        return modelMapper.map(saved, ProductionResponseDto.class);
    }


}

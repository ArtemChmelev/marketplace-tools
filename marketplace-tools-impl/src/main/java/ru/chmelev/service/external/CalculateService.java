package ru.chmelev.service.external;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.chmelev.controllers.feign.CalculateFeignClient;
import ru.chmelev.dto.calculate.request.ProductionInformationRequestDto;
import ru.chmelev.dto.calculate.response.CalculatedDataResponseDto;
import ru.chmelev.dto.production.request.ProductionRequestDto;
import ru.chmelev.dto.production.response.ProductionResponseDto;
import ru.chmelev.entity.Marketplace;
import ru.chmelev.entity.User;
import ru.chmelev.exception.NoFoundException;
import ru.chmelev.repositoriy.MarketplaceRepository;
import ru.chmelev.repositoriy.UserRepositories;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CalculateService {
    private final CalculateFeignClient feignClient;
    private final ModelMapper modelMapper;
    private final MarketplaceRepository marketplaceRepository;
    private final UserRepositories userRepositories;

    public ProductionResponseDto calculateFinalForOne(ProductionRequestDto productionRequestDto) {
        ProductionInformationRequestDto map = getProductionInformationRequestDto(productionRequestDto);
        CalculatedDataResponseDto calculatedDataResponseDto = feignClient.calculateFinalForOne(map);
        ProductionResponseDto outDto = modelMapper.map(productionRequestDto, ProductionResponseDto.class);
        outDto.setFinalPrice(calculatedDataResponseDto.getFinalPrice());
        return outDto;
    }

    public ProductionResponseDto calculateFinalOverall(ProductionRequestDto productionRequestDto) {
        ProductionInformationRequestDto map = getProductionInformationRequestDto(productionRequestDto);
        CalculatedDataResponseDto calculatedDataResponseDto = feignClient.calculateFinalOverall(map);
        ProductionResponseDto outDto = modelMapper.map(productionRequestDto, ProductionResponseDto.class);
        outDto.setFinalPrice(calculatedDataResponseDto.getFinalPrice());
        return outDto;
    }


    public ProductionResponseDto calculateProfitForOne(ProductionRequestDto productionRequestDto) {
        ProductionInformationRequestDto map = getProductionInformationRequestDto(productionRequestDto);
        CalculatedDataResponseDto calculatedDataResponseDto = feignClient.calculateProfitForOne(map);
        ProductionResponseDto outDto = modelMapper.map(productionRequestDto, ProductionResponseDto.class);
        outDto.setProfitPercentage(calculatedDataResponseDto.getProfitPercentage());
        return outDto;
    }

    public ProductionResponseDto calculateProfitOverall(ProductionRequestDto productionRequestDto) {
        ProductionInformationRequestDto map = getProductionInformationRequestDto(productionRequestDto);
        CalculatedDataResponseDto calculatedDataResponseDto = feignClient.calculateProfitOverall(map);
        ProductionResponseDto outDto = modelMapper.map(productionRequestDto, ProductionResponseDto.class);
        outDto.setProfitPercentage(calculatedDataResponseDto.getProfitPercentage());
        return outDto;
    }

    private ProductionInformationRequestDto getProductionInformationRequestDto(ProductionRequestDto productionRequestDto) {
        ProductionInformationRequestDto map = modelMapper.map(productionRequestDto, ProductionInformationRequestDto.class);
        Optional<Marketplace> marketplaceOptional = marketplaceRepository.findById(productionRequestDto.getMarketplaceId());
        Marketplace marketplace = marketplaceOptional.orElseThrow(() -> new NoFoundException("Маркетплейс не найден"));
        map.setCommissionMarketplace(marketplace.getCommission());
        Optional<User> usersOptional = userRepositories.findById(productionRequestDto.getUserId());
        User user = usersOptional.orElseThrow(() -> new NoFoundException("Пользователь не найден"));
        map.setTax(user.getTaxationType().getTaxPercent());
        return map;
    }

}

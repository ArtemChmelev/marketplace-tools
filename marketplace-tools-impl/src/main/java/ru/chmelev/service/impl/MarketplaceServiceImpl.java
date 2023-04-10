package ru.chmelev.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.chmelev.dto.marketplace.request.MarketplaceRequestDto;
import ru.chmelev.dto.marketplace.request.UpdateContactsDto;
import ru.chmelev.dto.marketplace.response.MarketplaceResponseDto;
import ru.chmelev.entity.Marketplace;
import ru.chmelev.exception.NoFoundException;
import ru.chmelev.repositoriy.MarketplaceRepository;
import ru.chmelev.service.MarketplaceService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class MarketplaceServiceImpl implements MarketplaceService {
    private final MarketplaceRepository marketplaceRepository;
    private final ModelMapper modelMapper;

    public MarketplaceServiceImpl(MarketplaceRepository marketplaceRepository, ModelMapper modelMapper) {
        this.marketplaceRepository = marketplaceRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public MarketplaceResponseDto createMarketplace(MarketplaceRequestDto marketplaceRequestDto) {
        Marketplace forSave = modelMapper.map(marketplaceRequestDto, Marketplace.class);
        Marketplace saved = marketplaceRepository.save(forSave);

        return modelMapper.map(saved, MarketplaceResponseDto.class);
    }
    @Override
    public MarketplaceResponseDto readById(Long id) {
        Optional<Marketplace> byId = marketplaceRepository.findById(id);
        Marketplace foundMarketplace = byId
                .orElseThrow(() -> new NoFoundException(String.format("Маркетплейс не найден по данному id %d", id)));
        return modelMapper.map(foundMarketplace, MarketplaceResponseDto.class);
    }
    @Override
    public MarketplaceResponseDto readByName(String name) {
        Optional<Marketplace> byName = marketplaceRepository.findByName(name);
        Marketplace foundMarketplace = byName
                .orElseThrow(() -> new NoFoundException(String.format("Маркетплейс не найден по данному имени %s", name)));
        return modelMapper.map(foundMarketplace, MarketplaceResponseDto.class);


    }
    @Override
    public MarketplaceResponseDto update(Long id, MarketplaceRequestDto marketplaceRequestDto) {
        marketplaceRepository.findById(id)
                .orElseThrow(() -> new NoFoundException(String.format("Маркетплейс не найден по данному id %d", id)));
        Marketplace forUpdate = modelMapper.map(marketplaceRequestDto, Marketplace.class);
        forUpdate.setId(id);
        Marketplace beforeUpdate = marketplaceRepository.save(forUpdate);
        return modelMapper.map(beforeUpdate, MarketplaceResponseDto.class);

    }
    @Override
    public List<MarketplaceResponseDto> readAll(String name) {
        List<Marketplace> all;
        if (Objects.equals(name, "")) {
            all = marketplaceRepository.findAll();
        } else {
            String postgresLike = "%%%s%%".formatted(name);
            all = marketplaceRepository.findAllByNameLikeIgnoreCase(postgresLike);
        }
        return all.stream()
                .map(marketplace -> modelMapper.map(marketplace, MarketplaceResponseDto.class))
                .toList();
    }
    @Override
    public void deleteById(Long id) {
        marketplaceRepository.findById(id)
                .orElseThrow(() -> new NoFoundException(String.format("Маркетплейс не найден по данному id %d", id)));
        marketplaceRepository.deleteById(id);
    }
    @Override
    public MarketplaceResponseDto updateContacts(Long id, UpdateContactsDto updateContactsDto) {
        Marketplace marketplace = marketplaceRepository.findById(id)
                .orElseThrow(() -> new NoFoundException(String.format("Маркетплейс не найден по данному id %d", id)));
        marketplace.setMarketplacePhoneNumber(updateContactsDto.getMarketplacePhoneNumber());
        marketplace.setMarketplaceEmail(updateContactsDto.getMarketplaceEmail());
        Marketplace afterUpdate = marketplaceRepository.save(marketplace);

        return modelMapper.map(afterUpdate, MarketplaceResponseDto.class);
    }
}

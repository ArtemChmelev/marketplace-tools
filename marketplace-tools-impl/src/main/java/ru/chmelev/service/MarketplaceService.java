package ru.chmelev.service;

import ru.chmelev.dto.marketplace.MarketplaceRequestDto;
import ru.chmelev.dto.marketplace.UpdateContactsDto;
import ru.chmelev.dto.marketplace.response.MarketplaceResponseDto;

import java.util.List;

public interface MarketplaceService {
    MarketplaceResponseDto createMarketplace(MarketplaceRequestDto marketplaceRequestDto);

    MarketplaceResponseDto readById(Long id);

    MarketplaceResponseDto readByName(String name);

    MarketplaceResponseDto update(Long id, MarketplaceRequestDto marketplaceRequestDto);

    List<MarketplaceResponseDto> readAll(String name);

    void deleteById(Long id);

    MarketplaceResponseDto updateContacts(Long id, UpdateContactsDto updateContactsDto);
}

package ru.chmelev.controllerimpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.chmelev.controller.MarketplaceController;
import ru.chmelev.dto.marketplace.MarketplaceRequestDto;
import ru.chmelev.dto.marketplace.UpdateContactsDto;
import ru.chmelev.dto.marketplace.response.MarketplaceResponseDto;
import ru.chmelev.service.MarketplaceService;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class MarketplaceControllerImpl implements MarketplaceController {

    private final MarketplaceService marketplaceService;
    @Override
    public ResponseEntity<?> create(MarketplaceRequestDto marketplaceRequestDto) {

        log.info("Request for creating marketplace:{}",marketplaceRequestDto);

        MarketplaceResponseDto marketplaceResponseDto = marketplaceService.createMarketplace(marketplaceRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(marketplaceResponseDto);
    }

    @Override
    public ResponseEntity<MarketplaceResponseDto> findById(Long id) {
        log.info("Request for find by id:{}",id);
        MarketplaceResponseDto marketplaceResponseDto = marketplaceService.readById(id);
        return ResponseEntity.ok(marketplaceResponseDto);
    }

    @Override
    public ResponseEntity<?> findByName(String name) {
        log.info("Request for find by name:{}",name);
        MarketplaceResponseDto marketplaceResponseDto = marketplaceService.readByName(name);
        return ResponseEntity.ok(marketplaceResponseDto);
    }

    @Override
    public ResponseEntity<?> update(Long id, MarketplaceRequestDto marketplaceRequestDto) {
        log.info("Request for update by id:{},dto:{}",id,marketplaceRequestDto);
        MarketplaceResponseDto marketplaceResponseDto = marketplaceService.update(id,marketplaceRequestDto);
        return ResponseEntity.ok(marketplaceResponseDto);
    }

    @Override
    public ResponseEntity<?> findAll(String name) {
        log.info("Request find all with name filter:{}",name);
        List<MarketplaceResponseDto>marketplaces = marketplaceService.readAll(name);
        return ResponseEntity.ok(marketplaces);
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        log.info("Request delete marketplace by id:{}",id);
        marketplaceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> updateContacts(Long id, UpdateContactsDto updateContactsDto) {
        log.info("Request update contacts:{} marketplace by id:{}",updateContactsDto,id);
        MarketplaceResponseDto marketplaceResponseDto = marketplaceService.updateContacts(id,updateContactsDto);
        return ResponseEntity.ok(marketplaceResponseDto);
    }


}

package ru.chmelev.dto.marketplace.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.chmelev.enums.MarketplaceTypes;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketplaceResponseDto {

    private long id;

    private String name;

    private MarketplaceTypes marketplaceTypes;

    private String marketplacePhoneNumber;

    private String marketplaceEmail;

    private String marketplaceInn;

    private boolean work;
}

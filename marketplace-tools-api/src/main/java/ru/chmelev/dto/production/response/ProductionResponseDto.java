package ru.chmelev.dto.production.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionResponseDto {

    private Long productionId;

    private Long marketplaceId;

    private Long userId;

    private Integer count;

    private Double cost;

    private Double overallCost;

    private Double profitPercentage;

    private Double finalPrice;
}

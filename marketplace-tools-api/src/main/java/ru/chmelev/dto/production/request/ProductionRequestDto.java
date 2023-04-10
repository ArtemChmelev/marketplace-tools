package ru.chmelev.dto.production.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.chmelev.validate.annotation.CheckProductionInfo;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CheckProductionInfo
public class ProductionRequestDto {
    @NotNull(message = "Необходимо указать marketplaceId")
    private Long marketplaceId;

    @NotNull(message = "Необходимо указать userId")
    private Long userId;

    private Integer count;

    private Double cost;

    private Double overallCost;

    private Double profitPercentage;

    private Double finalPrice;

}

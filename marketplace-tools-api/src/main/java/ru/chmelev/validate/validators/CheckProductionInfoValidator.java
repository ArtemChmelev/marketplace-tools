package ru.chmelev.validate.validators;

import ru.chmelev.dto.production.request.ProductionRequestDto;
import ru.chmelev.validate.annotation.CheckProductionInfo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckProductionInfoValidator implements ConstraintValidator<CheckProductionInfo, ProductionRequestDto> {
    @Override
    public boolean isValid(ProductionRequestDto production, ConstraintValidatorContext context) {
        if (production.getCost() == null && production.getOverallCost() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Необходимо указать только одно поле: cost или overallCost").addConstraintViolation();
            return false;
        }
        if (production.getCost() != null && production.getOverallCost() != null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Необходимо указать только одно поле: cost или overallCost").addConstraintViolation();
            return false;
        }
        if (production.getProfitPercentage() == null && production.getFinalPrice() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Необходимо указать одно поле: profitPercentage или finalPrice").addConstraintViolation();
            return false;
        }
        if (production.getProfitPercentage() != null && production.getFinalPrice() != null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Необходимо указать только одно поле: profitPercentage или finalPrice").addConstraintViolation();
            return false;
        }
            return true;
    }
}

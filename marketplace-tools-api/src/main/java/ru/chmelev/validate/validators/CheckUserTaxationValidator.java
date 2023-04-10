package ru.chmelev.validate.validators;

import ru.chmelev.dto.users.request.UsersRequestDto;
import ru.chmelev.validate.annotation.CheckUserTaxation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import static ru.chmelev.enums.OrganizationType.OOO;
import static ru.chmelev.enums.OrganizationType.SELF_EMPLOYED;
import static ru.chmelev.enums.TaxationType.*;

public class CheckUserTaxationValidator implements ConstraintValidator<CheckUserTaxation, UsersRequestDto> {
    @Override
    public boolean isValid(UsersRequestDto user, ConstraintValidatorContext context) {
        if (user.getOrganizationType() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(" Поле organizationType должно быть заполнено").addConstraintViolation();
            return false;
        }
        if (user.getTaxationType() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(" Поле taxationType должно быть заполнено").addConstraintViolation();
            return false;
        }

            if (user.getOrganizationType() == SELF_EMPLOYED && user.getTaxationType() != NPD) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("При organizationType SelfEmployed поле taxationType может быть только NPD ")
                        .addConstraintViolation();
                return false;
            }
            if (user.getOrganizationType() == OOO && user.getTaxationType() == NPD) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("При organizationType OOO поле taxationType не может быть NPD ")
                        .addConstraintViolation();
                return false;
            }
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Иформация должна быть заполнена").addConstraintViolation();
            return user.getTaxationType() != null;
        }
    }

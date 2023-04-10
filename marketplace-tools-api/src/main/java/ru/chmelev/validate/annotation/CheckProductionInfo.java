package ru.chmelev.validate.annotation;

import ru.chmelev.validate.validators.CheckProductionInfoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {CheckProductionInfoValidator.class})
@Target({ANNOTATION_TYPE, TYPE, FIELD, PARAMETER, RECORD_COMPONENT})
@Retention(RUNTIME)
public @interface CheckProductionInfo {

    String message() default "UNKNOWN ERROR ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

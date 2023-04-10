package ru.chmelev.dto.marketplace.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static ru.chmelev.constants.ValidationConstants.PHONE_NUMBER_REGEX;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateContactsDto {

    @Pattern(regexp = PHONE_NUMBER_REGEX,message = "Неправильный формат телефона")
    @NotBlank(message = "Номер телефона должен быть заполнен")
    private String marketplacePhoneNumber;

    @Email(message = "Неправильный формат электронной почты")
    private String marketplaceEmail;
}

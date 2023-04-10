package ru.chmelev.dto.marketplace.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.chmelev.enums.MarketplaceTypes;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static ru.chmelev.constants.ValidationConstants.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketplaceRequestDto {

    @NotBlank(message = "Name должно быть заполнено")
    private String name;

    @NotNull(message = "Тип должен быть заполнен")
    private MarketplaceTypes marketplaceTypes;

    @Pattern(regexp = PHONE_NUMBER_REGEX,message = "Неправильный формат телефона")
    @NotBlank(message = "Номер телефона должен быть заполнен")
    private String marketplacePhoneNumber;

    @Email(message = "Неправильный формат электронной почты")
    private String marketplaceEmail;

    @NotBlank(message = "Инн не может быть пустым")
    private String marketplaceInn;

    @NotNull(message = "Комиссия должна быть указана")
    private Integer commission;

    private boolean work;
}

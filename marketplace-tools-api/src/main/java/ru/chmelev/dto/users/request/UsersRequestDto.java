package ru.chmelev.dto.users.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.chmelev.enums.OrganizationType;
import ru.chmelev.enums.TaxationType;
import ru.chmelev.validate.annotation.CheckUserTaxation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

import static ru.chmelev.constants.ValidationConstants.PHONE_NUMBER_REGEX;
@Data
@NoArgsConstructor
@AllArgsConstructor
@CheckUserTaxation
public class UsersRequestDto {

    @NotBlank(message = "Поле Username должно быть заполнено")
    private String userName;

    @NotBlank(message = "Поле имя должно быть заполнено")
    private String firstName;

    @NotBlank(message = "Поле фамилия должно быть заполнено")
    private String lastName;

    @Pattern(regexp = PHONE_NUMBER_REGEX,message = "Неправильный формат телефона")
    @NotBlank(message = "Номер телефона должен быть заполнен")
    private String phoneNumber;

    @Email(message = "Неправильный формат электронной почты")
    private String email;

    @NotNull(message = "Типо организации должен быть указан")
    private OrganizationType organizationType;

    private TaxationType taxationType;

    private List<Long> marketplaces;

}

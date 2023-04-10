package ru.chmelev.dto.users.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.chmelev.dto.users.Users;
import ru.chmelev.enums.OrganizationType;
import ru.chmelev.enums.TaxationType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponseDto implements Users {

    int id;

    private String userName;

    @JsonIgnore
    private String firstName;

    @JsonIgnore
    private String lastName;

    private String phoneNumber;

    private String email;

    private LocalDateTime dateCreate;

    private LocalDateTime dateUpdate;

    private OrganizationType organizationType;

    private TaxationType taxationType;

    public String getFullName(){
        return String.format("%s %s",firstName,lastName);
    }


}

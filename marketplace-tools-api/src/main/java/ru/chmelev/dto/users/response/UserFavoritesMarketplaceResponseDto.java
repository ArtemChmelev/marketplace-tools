package ru.chmelev.dto.users.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.chmelev.dto.marketplace.response.MarketplaceResponseDto;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFavoritesMarketplaceResponseDto implements User {

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

    private List<MarketplaceResponseDto>marketplaces;

    public String getFullName(){
        return String.format("%s %s",firstName,lastName);
    }


}

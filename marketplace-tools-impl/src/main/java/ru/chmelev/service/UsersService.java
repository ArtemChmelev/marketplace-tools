package ru.chmelev.service;

import ru.chmelev.dto.users.UsersRequestDto;
import ru.chmelev.dto.users.response.User;
import ru.chmelev.dto.users.response.UserFavoritesMarketplaceResponseDto;
import ru.chmelev.dto.users.response.UsersResponseDto;

import java.util.List;

public interface UsersService {

    User createUsers(UsersRequestDto usersRequestDto);

    UsersResponseDto readById(Long id);

    void deleteById(Long id);

    UsersResponseDto update(Long id, UsersRequestDto usersRequestDto);


    List<UsersResponseDto>readAll(String filter);


    UsersResponseDto readByUserName(String userName);

    UserFavoritesMarketplaceResponseDto addMarketplace(Long userId, Long marketplaceId);
}

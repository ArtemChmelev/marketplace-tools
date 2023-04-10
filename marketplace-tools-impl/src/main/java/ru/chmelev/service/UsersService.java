package ru.chmelev.service;

import ru.chmelev.dto.users.request.UsersRequestDto;
import ru.chmelev.dto.users.Users;
import ru.chmelev.dto.users.response.UsersFavoritesMarketplaceResponseDto;
import ru.chmelev.dto.users.response.UsersResponseDto;

import java.util.List;

public interface UsersService {

    Users createUsers(UsersRequestDto usersRequestDto);

    UsersResponseDto readById(Long id);

    void deleteById(Long id);

    UsersResponseDto update(Long id, UsersRequestDto usersRequestDto);


    List<UsersResponseDto>readAll(String filter);


    UsersResponseDto readByUserName(String userName);

    UsersFavoritesMarketplaceResponseDto addMarketplace(Long userId, Long marketplaceId);
}

package ru.chmelev.controllerimpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.chmelev.controllers.UsersController;
import ru.chmelev.dto.users.request.UsersRequestDto;
import ru.chmelev.dto.users.Users;
import ru.chmelev.dto.users.response.UsersFavoritesMarketplaceResponseDto;
import ru.chmelev.dto.users.response.UsersResponseDto;
import ru.chmelev.service.UsersService;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class UsersControllerImpl implements UsersController {

    private final UsersService usersService;

    @Override
    public ResponseEntity<?> create(UsersRequestDto usersRequestDto) {
        log.info("Request for create user:{}", usersRequestDto);
        Users user = usersService.createUsers(usersRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Override
    public ResponseEntity<?> findAll(String filter) {
        log.info("Request for find all users");
        List<UsersResponseDto> allUsers = usersService.readAll(filter);
        return ResponseEntity.ok().body(allUsers);

    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        log.info("Request for find on user id:{}", id);
        UsersResponseDto usersResponseDto = usersService.readById(id);
        return ResponseEntity.ok().body(usersResponseDto);
    }

    @Override
    public ResponseEntity<?> findByUserName(String userName) {
        log.info("Request for find by user name :{}", userName);
        UsersResponseDto usersResponseDto = usersService.readByUserName(userName);
        return ResponseEntity.ok().body(usersResponseDto);
    }

    @Override
    public ResponseEntity<?> update(Long id, UsersRequestDto usersRequestDto) {
        log.info("Request for update user {} with id:{}", usersRequestDto, id);
        UsersResponseDto update = usersService.update(id, usersRequestDto);
        return ResponseEntity.ok().body(update);
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        log.info("Request for delete user with id:{}", id);
        usersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> addMarketplace(Long userId, Long marketplaceId) {
        log.info("Request for adding marketplace with id user: {} and id marketplace: {}", userId, marketplaceId);
        UsersFavoritesMarketplaceResponseDto userFavoritesMarketplaceResponseDto = usersService
                .addMarketplace(userId, marketplaceId);
        return ResponseEntity.ok(userFavoritesMarketplaceResponseDto);
    }
}

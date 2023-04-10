package ru.chmelev.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.chmelev.dto.users.Users;
import ru.chmelev.dto.users.request.UsersRequestDto;
import ru.chmelev.dto.users.response.UsersFavoritesMarketplaceResponseDto;
import ru.chmelev.dto.users.response.UsersResponseDto;
import ru.chmelev.entity.Marketplace;
import ru.chmelev.entity.User;
import ru.chmelev.exception.NoFoundException;
import ru.chmelev.repositoriy.MarketplaceRepository;
import ru.chmelev.repositoriy.UserRepositories;
import ru.chmelev.service.UsersService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {
    private final MarketplaceRepository marketplaceRepository;
    private final UserRepositories userRepositories;
    private final ModelMapper modelMapper;

    public UsersServiceImpl(MarketplaceRepository marketplaceRepository, UserRepositories userRepositories,
                            ModelMapper modelMapper) {
        this.marketplaceRepository = marketplaceRepository;
        this.userRepositories = userRepositories;
        this.modelMapper = modelMapper;
    }

    @Override
    public Users createUsers(UsersRequestDto usersRequestDto) {
        User save;
        if (Objects.equals(null,usersRequestDto.getMarketplaces())){
            log.info("Создание пользователя без маркетплейса");
            User forSave = getUserForSave(usersRequestDto);
            save = userRepositories.save(forSave);
            return modelMapper.map(save,UsersResponseDto.class);
        }
            List<Marketplace> allById = marketplaceRepository.findAllById(usersRequestDto.getMarketplaces());
            log.info("Создание пользователя с маркетплейсом");
            User forSave = getUserForSave(usersRequestDto);
            forSave.setMarketplaces(allById);
            save = userRepositories.save(forSave);

        return modelMapper.map(save, UsersFavoritesMarketplaceResponseDto.class);

    }
    private User getUserForSave(UsersRequestDto usersRequestDto){
        User forSave = modelMapper.map(usersRequestDto, User.class);
        forSave.setDateCreate(LocalDateTime.now());
        forSave.setDateUpdate(LocalDateTime.now());
        return forSave;
    }

    @Override
    public UsersResponseDto readById(Long id) {
        Optional<User>byId = userRepositories.findById(id);
        User foundUser = byId
                .orElseThrow(()->new NoFoundException(String.format("Пользоватедь не найден по данному %d",id)));
        return modelMapper.map(foundUser,UsersResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        userRepositories.findById(id)
                .orElseThrow(()->new NoFoundException(String.format("Пользоватедь не найден по данному %d",id)));
        userRepositories.deleteById(id);

    }

    @Override
    public UsersResponseDto update(Long id, UsersRequestDto usersRequestDto) {
        User beforeUpdate = userRepositories.findById(id)
                .orElseThrow(() -> new NoFoundException(String.format("Пользоватедь не найден по данному %d", id)));
        User forUpdate = modelMapper.map(usersRequestDto, User.class);
        forUpdate.setDateCreate(beforeUpdate.getDateCreate());
        forUpdate.setDateUpdate(LocalDateTime.now());
        forUpdate.setId(beforeUpdate.getId());
        User save = userRepositories.save(forUpdate);

        return modelMapper.map(save,UsersResponseDto.class);
    }

    @Override
    public List<UsersResponseDto> readAll(String filter) {
        List<User>list;
        if (Objects.equals(filter,"")){
            list = userRepositories.findAll();
        }else {
            String postgresLike = "%%%s%%".formatted(filter);
            list = userRepositories.findAllByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(postgresLike,postgresLike);
        }
        return list.stream()
                .map(i->modelMapper.map(i,UsersResponseDto.class))
                .toList();


    }


    @Override
    public UsersResponseDto readByUserName(String userName) {
        User byUserName = userRepositories.findByUserName(userName);
        return modelMapper.map(byUserName,UsersResponseDto.class);
    }

    @Override
    public UsersFavoritesMarketplaceResponseDto addMarketplace(Long userId, Long marketplaceId) {
        Marketplace marketplace = marketplaceRepository.findById(marketplaceId)
                .orElseThrow(() -> new NoFoundException(String.format("Маркетплейс не найден по данному %d", marketplaceId)));
        User user = userRepositories.findById(userId)
                .orElseThrow(() -> new NoFoundException(String.format("Пользоватедь не найден по данному %d", userId)));
        user.getMarketplaces().add(marketplace);
        User save = userRepositories.save(user);
        return modelMapper.map(save, UsersFavoritesMarketplaceResponseDto.class);
    }
}

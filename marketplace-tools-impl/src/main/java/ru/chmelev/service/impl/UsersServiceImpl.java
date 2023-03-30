package ru.chmelev.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.chmelev.dto.users.UsersRequestDto;
import ru.chmelev.dto.users.response.User;
import ru.chmelev.dto.users.response.UserFavoritesMarketplaceResponseDto;
import ru.chmelev.dto.users.response.UsersResponseDto;
import ru.chmelev.entity.Marketplace;
import ru.chmelev.entity.Users;
import ru.chmelev.exception.NoFoundException;
import ru.chmelev.repositoriy.MarketplaceRepository;
import ru.chmelev.repositoriy.UsersRepositories;
import ru.chmelev.service.UsersService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {
    private final MarketplaceRepository marketplaceRepository;
    private final UsersRepositories usersRepositories;
    private final ModelMapper modelMapper;

    public UsersServiceImpl(MarketplaceRepository marketplaceRepository, UsersRepositories usersRepositories,
                            ModelMapper modelMapper) {
        this.marketplaceRepository = marketplaceRepository;
        this.usersRepositories = usersRepositories;
        this.modelMapper = modelMapper;
    }

    @Override
    public User createUsers(UsersRequestDto usersRequestDto) {
        Users save;
        if (Objects.equals(null,usersRequestDto.getMarketplaces())){
            Users forSave = getUserForSave(usersRequestDto);
            save = usersRepositories.save(forSave);
            return modelMapper.map(save,UsersResponseDto.class);
        }
            List<Marketplace> allById = marketplaceRepository.findAllById(usersRequestDto.getMarketplaces());
            Users forSave = getUserForSave(usersRequestDto);
            forSave.setMarketplaces(allById);
            save = usersRepositories.save(forSave);

        return modelMapper.map(save,UserFavoritesMarketplaceResponseDto.class);

    }
    private Users getUserForSave(UsersRequestDto usersRequestDto){
        Users forSave = modelMapper.map(usersRequestDto, Users.class);
        forSave.setDateCreate(LocalDateTime.now());
        forSave.setDateUpdate(LocalDateTime.now());
        return forSave;
    }

    @Override
    public UsersResponseDto readById(Long id) {
        Optional<Users>byId = usersRepositories.findById(id);
        Users foundUsers = byId
                .orElseThrow(()->new NoFoundException(String.format("Пользоватедь не найден по данному %d",id)));
        return modelMapper.map(foundUsers,UsersResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        usersRepositories.findById(id)
                .orElseThrow(()->new NoFoundException(String.format("Пользоватедь не найден по данному %d",id)));
        usersRepositories.deleteById(id);

    }

    @Override
    public UsersResponseDto update(Long id, UsersRequestDto usersRequestDto) {
        Users beforeUpdate = usersRepositories.findById(id)
                .orElseThrow(() -> new NoFoundException(String.format("Пользоватедь не найден по данному %d", id)));
        Users forUpdate = modelMapper.map(usersRequestDto, Users.class);
        forUpdate.setDateCreate(beforeUpdate.getDateCreate());
        forUpdate.setDateUpdate(LocalDateTime.now());
        forUpdate.setId(beforeUpdate.getId());
        Users save = usersRepositories.save(forUpdate);

        return modelMapper.map(save,UsersResponseDto.class);
    }

    @Override
    public List<UsersResponseDto> readAll(String filter) {
        List<Users>list;
        if (Objects.equals(filter,"")){
            list = usersRepositories.findAll();
        }else {
            String postgresLike = "%%%s%%".formatted(filter);
            list = usersRepositories.findAllByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(postgresLike,postgresLike);
        }
        return list.stream()
                .map(i->modelMapper.map(i,UsersResponseDto.class))
                .toList();
    }


    @Override
    public UsersResponseDto readByUserName(String userName) {
        Users byUserName = usersRepositories.findByUserName(userName);
        return modelMapper.map(byUserName,UsersResponseDto.class);
    }

    @Override
    public UserFavoritesMarketplaceResponseDto addMarketplace(Long userId, Long marketplaceId) {
        Marketplace marketplace = marketplaceRepository.findById(marketplaceId)
                .orElseThrow(() -> new NoFoundException(String.format("Маркетплейс не найден по данному %d", marketplaceId)));
        Users user = usersRepositories.findById(userId)
                .orElseThrow(() -> new NoFoundException(String.format("Пользоватедь не найден по данному %d", userId)));
        user.getMarketplaces().add(marketplace);
        Users save = usersRepositories.save(user);
        return modelMapper.map(save,UserFavoritesMarketplaceResponseDto.class);
    }
}

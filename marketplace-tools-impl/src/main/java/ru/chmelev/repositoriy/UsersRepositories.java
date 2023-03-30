package ru.chmelev.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chmelev.entity.Users;

import java.util.List;

public interface UsersRepositories extends JpaRepository<Users, Long > {
    List<Users> findAllByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(String filter1,String filter2);

    Users findByUserName(String userName);
}

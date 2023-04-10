package ru.chmelev.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chmelev.entity.*;

import java.util.List;

public interface UserRepositories extends JpaRepository<User, Long > {
    List<User> findAllByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(String filter1, String filter2);

    User findByUserName(String userName);
}

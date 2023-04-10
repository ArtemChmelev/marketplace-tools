package ru.chmelev.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chmelev.entity.Production;

public interface ProductionRepositories extends JpaRepository<Production,Long> {

}

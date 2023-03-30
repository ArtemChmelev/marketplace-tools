package ru.chmelev.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chmelev.entity.Marketplace;

import java.util.List;
import java.util.Optional;

@Repository

public interface MarketplaceRepository extends JpaRepository<Marketplace,Long> {

    Optional<Marketplace> findByName(String name);

    List<Marketplace>findAllByNameLikeIgnoreCase(String name);
}

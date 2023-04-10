package ru.chmelev.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chmelev.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepositories extends JpaRepository<Product,Long> {

    Optional<Product> findByNameProduct(String name);

    List<Product> findAllByNameProductLikeIgnoreCase(String name);
}

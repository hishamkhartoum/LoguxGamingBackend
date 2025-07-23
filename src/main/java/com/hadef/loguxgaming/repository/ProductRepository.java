package com.hadef.loguxgaming.repository;

import ch.qos.logback.core.status.Status;
import com.hadef.loguxgaming.domain.entity.Genre;
import com.hadef.loguxgaming.domain.entity.Product;
import com.hadef.loguxgaming.domain.entity.Tag;
import com.hadef.loguxgaming.domain.value.ProductStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByGenresContaining(Genre genres);
    List<Product> findAllByStatusAndGenresContaining(ProductStatus status, Genre genre);
    List<Product> findAllByStatusAndGenresContainingAndTagsContaining(
            ProductStatus status, Genre genres, Tag tags);

    List<Product> findAllByStatusAndTagsContaining(ProductStatus status, Tag tags);

    List<Product> findAllByStatus(ProductStatus productStatus);

    @Transactional
    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.genres LEFT JOIN FETCH p.tags WHERE p.id = :id")
    Optional<Product> findByIdWithGenresAndTags(@Param("id") UUID id);
}

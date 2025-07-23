package com.hadef.loguxgaming.repository;

import com.hadef.loguxgaming.domain.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {

    @Query("SELECT C FROM Genre C LEFT JOIN FETCH C.products")
    List<Genre> findAllWithProductsCount();
    List<Genre> findByNameIn(Set<String> name);

}

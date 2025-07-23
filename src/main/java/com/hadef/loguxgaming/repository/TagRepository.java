package com.hadef.loguxgaming.repository;

import com.hadef.loguxgaming.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {

    @Query("SELECT C FROM Tag C LEFT JOIN FETCH C.products")
    List<Tag> findAllWithProductsCount();
    List<Tag> findByNameIn(Set<String> names);
}

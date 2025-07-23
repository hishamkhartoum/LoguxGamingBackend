package com.hadef.loguxgaming.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @ManyToMany(mappedBy = "genres")
    private Set<Product> products;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id) && Objects.equals(name, genre.name) && Objects.equals(products, genre.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, products);
    }
}

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
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToMany(mappedBy = "tags")
    private Set<Product> products;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) && Objects.equals(name, tag.name) && Objects.equals(products, tag.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, products);
    }
}

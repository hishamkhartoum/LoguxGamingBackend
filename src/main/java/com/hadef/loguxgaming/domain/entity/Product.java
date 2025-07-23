package com.hadef.loguxgaming.domain.entity;

import com.hadef.loguxgaming.domain.value.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private BigDecimal discount;
    @ManyToMany
    @JoinTable(
            name = "product_genres",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;
    @ManyToMany
    @JoinTable(
        name = "product_tags",
        joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns =@JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(image, product.image) && Objects.equals(quantity, product.quantity) && Objects.equals(price, product.price) && Objects.equals(user, product.user) && Objects.equals(discount, product.discount) && Objects.equals(genres, product.genres) && Objects.equals(tags, product.tags) && status == product.status && Objects.equals(createdAt, product.createdAt) && Objects.equals(updatedAt, product.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, image, quantity, price, user, discount, genres, tags, status, createdAt, updatedAt);
    }
}

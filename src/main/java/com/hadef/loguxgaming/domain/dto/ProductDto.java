package com.hadef.loguxgaming.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hadef.loguxgaming.domain.entity.User;
import com.hadef.loguxgaming.domain.value.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private UUID id;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private UserDto user;
    private Integer quantity;
    private BigDecimal discount;
    private Set<GenreDto> genres;
    private Set<TagDto> tags;
    private ProductStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

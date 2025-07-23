package com.hadef.loguxgaming.domain.dto;

import com.hadef.loguxgaming.domain.value.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {

    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal discount;
    @Builder.Default
    private Set<UUID> genresIds = new HashSet<>();
    @Builder.Default
    private Set<UUID> tagsIds = new HashSet<>();
    private ProductStatus status;
}

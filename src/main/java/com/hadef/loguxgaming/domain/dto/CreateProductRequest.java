package com.hadef.loguxgaming.domain.dto;

import com.hadef.loguxgaming.domain.value.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;
    @Builder.Default
    private Set<UUID> genresIds = new HashSet<>();
    @Builder.Default
    private Set<UUID> tagsIds = new HashSet<>();
    private ProductStatus status;
}

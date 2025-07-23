package com.hadef.loguxgaming.domain.dto;

import com.hadef.loguxgaming.domain.entity.Genre;
import com.hadef.loguxgaming.domain.entity.Tag;
import com.hadef.loguxgaming.domain.entity.User;
import com.hadef.loguxgaming.domain.value.ProductStatus;
import jakarta.persistence.*;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductDto {

    @NotBlank(message = "Name is required")
    @Size(min = 2,max = 20,message = "Name is between {min} and {max}")
    private String name;
    @NotBlank(message = "Description is required")
    @Size(min = 10,max = 50000,message = "Description is between {min} and {max}")
    private String description;
    @NotNull(message = "Quantity is required")
    private Integer quantity;
    @NotNull(message = "Price is required")
    private BigDecimal price;
    @Size(min = 1,max = 10, message = "Genre maximum {max} is allowed and minimum is {min}")
    @Builder.Default
    private Set<UUID> genresIds = new HashSet<>();
    @Size(min = 1,max = 10, message = "Tag maximum {max} is allowed and minimum is {min}")
    @Builder.Default
    private Set<UUID> tagsIds = new HashSet<>();
    @NotNull(message = "Genre status is required")
    private ProductStatus status;
}

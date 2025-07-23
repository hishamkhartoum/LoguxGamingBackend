package com.hadef.loguxgaming.domain.dto;

import com.hadef.loguxgaming.domain.entity.Product;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {
    private UUID id;
    private String name;
    private Integer productsCount;
}

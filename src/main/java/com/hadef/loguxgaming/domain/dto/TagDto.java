package com.hadef.loguxgaming.domain.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagDto {
    private UUID id;
    private String name;
    private Integer productCount;
}

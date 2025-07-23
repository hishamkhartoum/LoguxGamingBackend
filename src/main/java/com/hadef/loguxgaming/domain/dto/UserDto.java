package com.hadef.loguxgaming.domain.dto;


import com.hadef.loguxgaming.domain.entity.Product;
import com.hadef.loguxgaming.domain.value.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String email;
    private String fullName;
    private Long phoneNumber;
    private List<Product> products;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

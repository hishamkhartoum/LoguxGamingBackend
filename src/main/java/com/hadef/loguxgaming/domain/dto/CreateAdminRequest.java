package com.hadef.loguxgaming.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdminRequest {

    private String email;
    private String password;
    private String fullName;
    private Long phoneNumber;
}

package com.hadef.loguxgaming.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAdminDto {
    @NotNull(message = "User email is null")
    @NotEmpty(message = "Please enter an email")
    @Email(message = "Please enter a valid email")
    private String email;
    @NotNull(message = "User password is null")
    @NotEmpty(message = "Please enter a password")
    private String password;
    @NotNull(message = "User full name is null")
    @NotEmpty(message = "Please enter your full name")
    private String fullName;
    @NotNull(message = "User phone number is null")
    @NotEmpty(message = "Please enter your phone null")
    @Size(min = 12,max = 13, message = "Phone number is invalid")
    private Long phoneNumber;
}

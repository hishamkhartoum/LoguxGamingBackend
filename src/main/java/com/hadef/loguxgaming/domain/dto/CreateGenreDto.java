package com.hadef.loguxgaming.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGenreDto {
    @NotEmpty(message = "At least one genre")
    @Size(max = 10,message = "maximum {max} is allowed")
    private Set<
            @Pattern(regexp = "^[A-Za-z0-9\\- ]+$", message = "Genre name only contain letters, numbers, spaces, and hyphens")
            @Size(min = 3,max = 30, message = "Genre name must be between {min} to {max}")
            String> names = new HashSet<>() ;
}

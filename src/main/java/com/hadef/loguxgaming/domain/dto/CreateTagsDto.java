package com.hadef.loguxgaming.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTagsDto {
    @Size(max = 10,message = "maximum {max} is allowed")
    @NotEmpty(message = "At least must be one tag")
    private Set<
            @Pattern(regexp = "^[A-Za-z0-9\\- ]+$", message = "Tag name only contain letters, numbers, spaces, and hyphens")
            @Size(min = 3,max = 30, message = "Tag name must be between {min} to {max}")
            String> names;
}

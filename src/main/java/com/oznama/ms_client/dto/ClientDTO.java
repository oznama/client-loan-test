package com.oznama.ms_client.dto;

import com.oznama.ms_client.constants.ClientType;
import jakarta.validation.constraints.*;

import java.util.UUID;

public record ClientDTO(
        @NotNull(message = "Required id")
        String id,
        @NotBlank(message = "Required name")
        @Size(min = 2, max = 50, message = "Name must have length of {min} to {max}")
        @Pattern(regexp = "^[a-zA-Z\\s'-]+$", message = "Invalid name")
        String name,
        @NotBlank(message = "Required email")
        @Email(message = "Invalid email")
        String email,
        @NotNull(message = "Required age")
        @Min(value = 18, message = "Must be at least {value}")
        @Max(value = 70, message = "Must be less than {value}")
        Integer age,
        ClientType clientType
) {
    public ClientDTO {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}

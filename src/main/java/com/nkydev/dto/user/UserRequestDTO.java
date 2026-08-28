package com.nkydev.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank(message = "name is required")
        String name,
        @NotBlank(message = "email is required")
        @Email(message = "email format is invalid")
        String email,
        @NotBlank(message = "password is required")
        @Size(min = 8, message = "password must contain at least 8 characters")
        String password
) {}
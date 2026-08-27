package com.nkydev.dto.user;

import com.nkydev.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Integer id,
        String name,
        String email,
        Role role,
        LocalDateTime createdAt
) {}

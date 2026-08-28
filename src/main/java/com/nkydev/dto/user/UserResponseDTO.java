package com.nkydev.dto.user;

import com.nkydev.entity.Role;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Integer id,
        String name,
        String email,
        Role role,
        LocalDateTime createdAt
) {
}

package com.nkydev.dto.event;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record EventRequestDTO(
        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        String name,

        @Size(max = 500, message = "Description cannot exceed 500 characters")
        String description,

        @NotNull(message = "Date is required")
        @Future(message = "Date must be in the future")
        LocalDateTime date,

        @NotBlank(message = "Location is required")
        String location,

        @NotNull(message = "Capacity is required")
        @Min(value = 1, message = "Capacity must be at least 1")
        Integer capacity,

        @NotNull(message = "Category ID is required")
        Integer categoryId
) {}
package com.nkydev.dto.tickettype;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import javax.management.remote.JMXServerErrorException;
import java.math.BigDecimal;

public record TicketTypeRequestDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = true, message = "Price cannot be negative")
        BigDecimal price,

        @NotNull
        @Min(value = 1, message = "The quantity has to have at least 1")
        Integer quantity,

        @NotNull(message = "Event is required")
        Integer eventId
)
{}
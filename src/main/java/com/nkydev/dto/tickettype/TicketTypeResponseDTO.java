package com.nkydev.dto.tickettype;

import java.math.BigDecimal;

public record TicketTypeResponseDTO(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity,
        Integer availableQuantity,
        Integer idEvent
)
{}
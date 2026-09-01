package com.nkydev.dto.purchaseItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseItemRequestDTO(
        @NotNull(message = "ticket tyoe is required")
        Integer ticketTypeId,
        @NotNull(message = "quantity is required")
        @Positive(message = "the quantity must to be greater than zero")
        Integer quantity
) {
}

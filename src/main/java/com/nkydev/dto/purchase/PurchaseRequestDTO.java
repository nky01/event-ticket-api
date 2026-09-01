package com.nkydev.dto.purchase;

import com.nkydev.dto.purchaseItem.PurchaseItemRequestDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PurchaseRequestDTO(
        @NotNull(message = "The user ID is required")
        Long userId,

        @NotEmpty(message = "The purchase must contain at least one item")
        List<PurchaseItemRequestDTO> items
) {}
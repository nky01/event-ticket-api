package com.nkydev.dto.purchase;

import com.nkydev.entity.PurchaseStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PurchaseRequestDTO(
        @NotNull(message = "The user ID is required")
        Long userId
) {}
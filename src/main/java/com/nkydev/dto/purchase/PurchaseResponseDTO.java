package com.nkydev.dto.purchase;

import com.nkydev.entity.PurchaseStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PurchaseResponseDTO(
        Long id,
        Long userId,
        String userName,
        LocalDateTime purchaseDate,
        BigDecimal totalAmount,
        PurchaseStatus status
) {}
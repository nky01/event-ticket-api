package com.nkydev.dto.purchase;

import com.nkydev.dto.purchaseItem.PurchaseItemResponseDTO;
import com.nkydev.entity.enums.PurchaseStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PurchaseResponseDTO(
        Long id,
        Long userId,
        String userName,
        LocalDateTime purchaseDate,
        BigDecimal totalAmount,
        PurchaseStatus status,
        List<PurchaseItemResponseDTO> items
) {}
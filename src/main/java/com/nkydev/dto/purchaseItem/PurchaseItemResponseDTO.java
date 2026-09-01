package com.nkydev.dto.purchaseItem;

import java.math.BigDecimal;

public record PurchaseItemResponseDTO(
        Integer id,
        Integer ticketTipeId,
        String ticketTypeName,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal
) {
}
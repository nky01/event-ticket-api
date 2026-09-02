package com.nkydev.dto.payment;

import com.nkydev.entity.enums.PaymentMethod;
import com.nkydev.entity.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponseDTO(
        Long id,
        Long purchaseId,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        PaymentStatus status,
        String transactionId,
        LocalDateTime paymentDate,
        String message
) {}
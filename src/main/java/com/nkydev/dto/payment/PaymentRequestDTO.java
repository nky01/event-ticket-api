package com.nkydev.dto.payment;

import com.nkydev.entity.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PaymentRequestDTO(

        @NotNull(message = "purchase ID is required")
        Long purchaseId,

        @NotNull(message = "payment method is required")
        PaymentMethod paymentMethod,

        @NotNull(message = "amount is required")
        @Positive(message = "amount must be greater than zero")
        BigDecimal amount
) {}
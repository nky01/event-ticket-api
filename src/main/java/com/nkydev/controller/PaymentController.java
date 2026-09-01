package com.nkydev.controller;

import com.nkydev.dto.payment.PaymentRequestDTO;
import com.nkydev.dto.payment.PaymentResponseDTO;
import com.nkydev.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponseDTO processPayment(@Valid @RequestBody PaymentRequestDTO request) {
        return paymentService.processPayment(request);
    }

    @GetMapping("/purchases/{purchaseId}")
    public PaymentResponseDTO getPaymentByPurchaseId(@PathVariable Integer purchaseId) {
        return paymentService.getPaymentByPurchaseId(purchaseId);
    }
}
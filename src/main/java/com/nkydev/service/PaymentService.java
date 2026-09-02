package com.nkydev.service;

import com.nkydev.dto.payment.PaymentRequestDTO;
import com.nkydev.dto.payment.PaymentResponseDTO;
import com.nkydev.entity.Payment;
import com.nkydev.entity.enums.PaymentStatus;
import com.nkydev.entity.Purchase;
import com.nkydev.entity.enums.PurchaseStatus;
import com.nkydev.repository.PaymentRepository;
import com.nkydev.repository.PurchaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PurchaseRepository purchaseRepository;

    public PaymentService(PaymentRepository paymentRepository, PurchaseRepository purchaseRepository) {
        this.paymentRepository = paymentRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Transactional
    public PaymentResponseDTO processPayment(PaymentRequestDTO request) {
        //se busca la compra
        Purchase purchase = purchaseRepository.findById(request.purchaseId().intValue())
                .orElseThrow(() -> new RuntimeException("purchase not found with ID: " + request.purchaseId()));
        // verifico si esta en 'pendiente'
        if (purchase.getPurchaseStatus() != PurchaseStatus.PENDING) {
            throw new RuntimeException("purchase is already processed or cancelled");
        }
        // tambien que el precio coincida
        if (purchase.getTotalAmount().compareTo(request.amount()) != 0) {
            throw new RuntimeException("payment amount does not match purchase total amount");
        }
        // actualizar el estado de la compra a 'confirmado'
        purchase.setPurchaseStatus(PurchaseStatus.CONFIRMED);

        Payment payment = new Payment();
        payment.setPurchase(purchase);
        payment.setAmount(request.amount());
        payment.setPaymentMethod(request.paymentMethod());
        payment.setStatus(PaymentStatus.APPROVED);
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPaymentDate(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        return mapToResponseDTO(savedPayment);
    }

    public PaymentResponseDTO getPaymentByPurchaseId(Integer purchaseId) {
        Payment payment = paymentRepository.findByPurchaseId(purchaseId)
                .orElseThrow(() -> new RuntimeException("payment not found for purchase ID: " + purchaseId));

        return mapToResponseDTO(payment);
    }

    private PaymentResponseDTO mapToResponseDTO(Payment payment) {
        return new PaymentResponseDTO(
                payment.getId().longValue(),
                payment.getPurchase().getId().longValue(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getStatus(),
                payment.getTransactionId(),
                payment.getPaymentDate(),
                "payment processed successfully"
        );
    }
}
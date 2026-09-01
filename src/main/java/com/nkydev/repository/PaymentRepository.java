package com.nkydev.repository;

import com.nkydev.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Optional<Payment> findByPurchaseId(Integer purchaseId);
}
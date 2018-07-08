package com.example.payin.services;

import com.example.payin.domain.Refund;

import java.math.BigDecimal;
import java.util.UUID;

public interface RefundService {

    void executeRefund(UUID id, BigDecimal amount, UUID transactionId);
    void executeRefund(Refund refund);
}

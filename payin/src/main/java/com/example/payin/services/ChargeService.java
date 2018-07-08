package com.example.payin.services;

import com.example.payin.domain.Charge;

import java.math.BigDecimal;
import java.util.UUID;

public interface ChargeService {

    void executeCharge(UUID id, BigDecimal amount, UUID transactionId);
    void executeCharge(Charge charge);
}

package com.example.payin.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Charge {
    private UUID id;
    private UUID transactionId;
    private BigDecimal amount;

    public Charge(UUID id, UUID transactionId, BigDecimal amount) {
        this.id = id;
        this.transactionId = transactionId;
        this.amount = amount;
    }

    protected Charge() {
    }

    @Override
    public String toString() {
        return "Charge{" +
                "id=" + id +
                ", transactionId=" + transactionId +
                ", amount=" + amount +
                '}';
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}

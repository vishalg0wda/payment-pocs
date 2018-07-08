package com.example.payout.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class FundRelease {
    private UUID id;
    private UUID transactionId;
    private BigDecimal amount;

    public FundRelease() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "FundRelease{" +
                "id=" + id +
                ", transactionId=" + transactionId +
                ", amount=" + amount +
                '}';
    }
}

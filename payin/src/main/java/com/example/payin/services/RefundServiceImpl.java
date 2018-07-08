package com.example.payin.services;

import com.example.payin.domain.Refund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class RefundServiceImpl implements RefundService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RefundServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void executeRefund(UUID id, BigDecimal amount, UUID transactionId) {
        jdbcTemplate.update("INSERT INTO refund(id, transaction_id, amount)" +
                " VALUES (?, ?, ?)", id, transactionId, amount);
    }

    @Override
    public void executeRefund(Refund refund) {
        jdbcTemplate.update("INSERT INTO refund(id, transaction_id, amount)" +
                " VALUES (?, ?, ?)", refund.getId(), refund.getTransactionId(), refund.getAmount());
    }
}

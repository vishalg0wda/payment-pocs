package com.example.payin.services;

import com.example.payin.domain.Charge;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ChargeServiceImpl implements ChargeService {
    org.slf4j.Logger log = LoggerFactory.getLogger(ChargeServiceImpl.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ChargeServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void executeCharge(UUID id, BigDecimal amount, UUID transactionId) {
        log.info(jdbcTemplate.toString());
        jdbcTemplate.update("INSERT INTO charge(id, transaction_id, amount)" +
                "VALUES(?, ?, ?)", id, transactionId, amount);
    }

    @Override
    public void executeCharge(Charge charge) {
        log.info(charge.toString());
        jdbcTemplate.update("INSERT INTO charge(id, transaction_id, amount)" +
                "VALUES(?, ?, ?)", charge.getId(), charge.getTransactionId(), charge.getAmount());
    }
}

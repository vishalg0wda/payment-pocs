package com.example.payout.services;

import com.example.payout.domain.FundRelease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FundReleaseServiceImpl implements FundReleaseService {
    private JdbcTemplate jdbcTemplate;
    private RestTemplate restTemplate;

    @Autowired
    public FundReleaseServiceImpl(JdbcTemplate jdbcTemplate, RestTemplate restTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.restTemplate = restTemplate;
    }

    @Override
    public void releaseFunds(FundRelease release) {
        jdbcTemplate.update("INSERT into release(id, transaction_id, amount)" +
                " VALUES (?,?,?)", release.getId(), release.getTransactionId(), release.getAmount());
    }
}

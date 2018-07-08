package com.example.payout.services;

import com.example.payout.domain.FundRelease;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class FundReleaseServiceImpl implements FundReleaseService {

    private static Logger log = LoggerFactory.getLogger(FundReleaseServiceImpl.class);
    private JdbcTemplate jdbcTemplate;
    private RestTemplate restTemplate;

    @Value("${com.example.payin-service.url}")
    String payinServiceUrl;

    @Autowired
    public FundReleaseServiceImpl(JdbcTemplate jdbcTemplate, RestTemplate restTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.restTemplate = restTemplate;
    }

    @Override
    public void releaseFunds(FundRelease release) {
        if (!canReleaseFunds(release)) {
            log.warn("Cannot release funds for transaction-{}", release.getTransactionId());
            return;
        }

        jdbcTemplate.update("INSERT into release(id, transaction_id, amount)" +
                " VALUES (?,?,?)", release.getId(), release.getTransactionId(), release.getAmount());
    }

    private boolean canReleaseFunds(FundRelease release) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(payinServiceUrl)
                .path("/refunds")
                .queryParam("transactionId", release.getTransactionId())
                .queryParam("includeBalance", true)
                .build();
        log.info("URI: {}", uriComponents.toString());
        ResponseEntity<String> response =
                restTemplate.getForEntity(uriComponents.toString(), String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            return release.getAmount().compareTo(new BigDecimal(root.get("balance").asText())) < 1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}

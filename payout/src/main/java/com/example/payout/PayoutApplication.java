package com.example.payout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PayoutApplication {
    private static final Logger log = LoggerFactory.getLogger(PayoutApplication.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PayoutApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            log.info("Creating tables");

            jdbcTemplate.execute("CREATE TABLE release(" +
                    "id UUID, transaction_id UUID, amount DECIMAL)");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(PayoutApplication.class, args);
    }
}

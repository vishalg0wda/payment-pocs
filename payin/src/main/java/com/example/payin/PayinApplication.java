package com.example.payin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class PayinApplication {
    public static Logger log = LoggerFactory.getLogger(PayinApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PayinApplication.class, args);
    }

    JdbcTemplate jdbcTemplate;

    @Autowired
    public PayinApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            log.info("Creating tables");

            jdbcTemplate.execute("CREATE TABLE charge(" +
                "id UUID, transaction_id UUID, amount DECIMAL)");

            jdbcTemplate.execute("CREATE TABLE refund(" +
                    "id UUID, transaction_id UUID, amount DECIMAL)");
        };
    }
}

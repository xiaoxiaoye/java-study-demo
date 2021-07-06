package com.example.mybatiscommonmapperspringbootdemo.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserCountTask {
    private final JdbcTemplate jdbcTemplate;

    public UserCountTask(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void countUser() {
        Integer count = jdbcTemplate.queryForObject("SELECT count(*) FROM vos.user;", Integer.class);
        log.info("count live user: {}", count);
    }
}

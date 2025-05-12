package com.postgresql.reverbclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/db-test")
public class TestDBController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TestDBController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public String testDatabaseConnection() {
        try {
            // Simple query to test connection
            List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT current_timestamp as time");
            return "Database connection successful! Current timestamp: " + result.get(0).get("time");
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
}

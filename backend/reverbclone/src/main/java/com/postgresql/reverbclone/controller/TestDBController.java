package com.postgresql.reverbclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

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
    public String testDatabaseConnection(HttpServletRequest request) {
        try {
            // Simple query to test connection
            List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT current_timestamp as time");
            // added session id
            return "Database connection successful! Current timestamp: " + result.get(0).get("time") + " this is session id:" + request.getSession().getId();
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
}

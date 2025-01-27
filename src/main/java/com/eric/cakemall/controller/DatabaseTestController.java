package com.eric.cakemall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class DatabaseTestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/test-database")
    public String testDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return "Connected to: " + connection.getMetaData().getDatabaseProductName() +
                    ", Version: " + connection.getMetaData().getDatabaseProductVersion();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to connect to the database: " + e.getMessage();
        }
    }
}

package com.ra.ss9.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/structured-log")
@RequiredArgsConstructor
public class StructuredLogController {
    private static final Logger logger = LoggerFactory.getLogger(StructuredLogController.class);

    @GetMapping
    public String testLogging(@RequestParam(defaultValue = "testUser") String username) {
        logger.info("User {} is accessing structured logging API", username);
        return "Check the console for structured log output.";
    }
}
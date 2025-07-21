package com.ra.ss9.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demo")
@RequiredArgsConstructor
public class DemoController {
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
    @GetMapping("/divide")
    public String divide(@RequestParam int a, @RequestParam int b) {
        logger.info("Yêu cầu chia: {}/{}", a, b);
        try {
            int result = a / b;
            return "Kết quả: " + result;
        } catch (ArithmeticException ex) {
            logger.error("Lỗi chia cho 0: {}", ex.getMessage(), ex);
            return "Không thể chia cho 0!";
        }
    }
}

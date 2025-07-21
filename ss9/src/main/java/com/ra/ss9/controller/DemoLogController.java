package com.ra.ss9.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo-log")
@Slf4j
public class DemoLogController {

    @GetMapping("/trace")
    public String traceLog() {
        log.trace("Đã ghi log trace");
        return "Trace log đã được ghi!";
    }

    @GetMapping("/debug")
    public String debugLog() {
        log.debug("Đã ghi log debug");
        return "Debug log đã được ghi!";
    }

    @GetMapping("/info")
    public String infoLog() {
        log.info("Đã ghi log info");
        return "Info log đã được ghi!";
    }

    @GetMapping("/warning")
    public String warningLog() {
        log.warn("Đã ghi log warning");
        return "Warning log đã được ghi!";
    }

    @GetMapping("/error")
    public String errorLog() {
        log.error("Đã ghi log error");
        return "Error log đã được ghi!";
    }
}

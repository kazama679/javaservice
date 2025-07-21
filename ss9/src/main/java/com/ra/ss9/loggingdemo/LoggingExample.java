package com.ra.ss9.loggingdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingExample {
    public void logMessages() {
        log.trace("Log TRACE: chi tiết quá trình xử lý.");
        log.debug("Log DEBUG: thông tin phục vụ debug.");
        log.info("Log INFO: hệ thống đang hoạt động bình thường.");
        log.warn("Log WARN: cảnh báo, có thể gây lỗi.");
        log.error("Log ERROR: đã xảy ra lỗi.");
    }
}

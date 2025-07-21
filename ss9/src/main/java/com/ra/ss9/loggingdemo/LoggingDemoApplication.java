package com.ra.ss9.loggingdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingDemoApplication implements CommandLineRunner {

    @Autowired
    private LoggingExample loggingExample;

    public static void main(String[] args) {
        SpringApplication.run(LoggingDemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        loggingExample.logMessages();
    }
}

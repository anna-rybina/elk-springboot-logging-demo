package com.github.annarybina.elk_logging_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LogController {

    private static final Logger log = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/test")
    public String test() {
        log.info("Test endpoint called");
        return "Test OK";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) String name) {
        String message = "Hello, " + (name != null ? name : "World");
        log.info("Hello endpoint called with name: {}", name);
        return message;
    }

    @PostMapping("/log")
    public String createLog(@RequestBody String message) {
        log.warn("Warning log created: {}", message);
        return "Logged: " + message;
    }

    @GetMapping("/error")
    public String error() {
        try {
            throw new RuntimeException("Test error for ELK");
        } catch (Exception e) {
            log.error("Error endpoint triggered", e);
            return "Error logged";
        }
    }
}
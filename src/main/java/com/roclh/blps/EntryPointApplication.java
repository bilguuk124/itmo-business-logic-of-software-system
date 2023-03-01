package com.roclh.blps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EntryPointApplication {

    private static final Logger log = LogManager.getLogger(EntryPointApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(EntryPointApplication.class, args);
        log.info("Пять минут -> полет нормальный");
    }
}

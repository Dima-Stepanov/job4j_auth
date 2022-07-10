package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Job4jAuthApplication {
    private static final Logger LOG = LoggerFactory.getLogger(Job4jAuthApplication.class.getSimpleName());

    public static void main(String[] args) {
        SpringApplication.run(Job4jAuthApplication.class, args);
        LOG.info("Go to URI http://localhost:8080/person/");
    }
}

package com.app.trycatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TrycatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrycatchApplication.class, args);
    }

}

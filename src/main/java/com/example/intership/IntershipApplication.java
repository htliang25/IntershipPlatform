package com.example.intership;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class IntershipApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntershipApplication.class, args);
    }

}

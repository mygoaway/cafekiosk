package com.example.cafekisokapi;

import com.example.common.response.ControllerAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ControllerAdvice.class)
public class CafekisokApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafekisokApiApplication.class, args);
    }

}

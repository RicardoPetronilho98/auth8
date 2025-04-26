package com.playground.auth8.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.playground")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
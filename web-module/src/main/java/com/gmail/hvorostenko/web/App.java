package com.gmail.hvorostenko.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
@ComponentScan({"com.gmail.hvorostenko.web",
        "com.gmail.hvorostenko.service",
        "com.gmail.hvorostenko.repository"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

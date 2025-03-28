package com.example.Library.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Getter
    private String username;

    public void login(String username) {
            System.out.println("User: " + username + " logged in successfully");
            this.username = username;
    }

    public void logout() {
            System.out.println("User " + username + " logged out successfully");
            username = null;
    }

    @PostConstruct
    public void init() {
        System.out.println("Loading user data...");
    }

    private void initMethod() {
        System.out.println("Welcome to the library");
    }



    @PreDestroy
    public void cleanup() {
        logout();
        System.out.println("Please wait for the data to be saved...");{
        }
    }

    private void destroyMethod() {
        System.out.println("Saving successful, Thank you for visiting us!");
    }
}

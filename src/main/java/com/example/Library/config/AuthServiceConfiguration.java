package com.example.Library.config;

import com.example.Library.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.Library.service")
public class AuthServiceConfiguration {



    @Bean(initMethod = "initMethod" , destroyMethod = "destroyMethod")
    public AuthService authService() {
        return new AuthService();
    }
}

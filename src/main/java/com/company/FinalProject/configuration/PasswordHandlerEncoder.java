package com.company.FinalProject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordHandlerEncoder {
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder(12);
    }
}

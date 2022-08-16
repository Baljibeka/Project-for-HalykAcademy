package com.company.FinalProject.configuration;

import com.company.FinalProject.exception.MyBasicAuthenticationEntryPoint;
import com.company.FinalProject.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo){
        return new UserDetailsServiceImpl(userRepo);
    }

    @Autowired
    private MyBasicAuthenticationEntryPoint myBasicAuthenticationEntryPoint;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .anyRequest()
                .authenticated()
                .and().formLogin()
                .and().httpBasic()
         .authenticationEntryPoint(myBasicAuthenticationEntryPoint);
        return http.build();
    }
}

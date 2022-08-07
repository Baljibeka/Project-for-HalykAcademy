package com.company.FinalProject.configuration;

import com.company.FinalProject.repo.UserRepository;
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

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/orders").permitAll()
                .antMatchers("/orders/order/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/authors").permitAll()
                .antMatchers("/authors/author/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/books").permitAll()
                .antMatchers("/books/book/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/genres").permitAll()
                .antMatchers("/genres/genre/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/publishers").permitAll()
                .antMatchers("publishers/publisher/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/users").permitAll()
                .antMatchers("users/user/**").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }
}

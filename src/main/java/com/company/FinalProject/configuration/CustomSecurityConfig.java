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
                .antMatchers(HttpMethod.GET, "/orders/order").permitAll()
                .antMatchers("/orders/order/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/authors/author").permitAll()
                .antMatchers("/authors/author/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/books/book").permitAll()
                .antMatchers("/books/book/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/genres/genre").permitAll()
                .antMatchers("/genres/genre/**").hasAuthority("ADMIN")
                .and().formLogin();

//        return http.csrf().disable().authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/orders/order").authenticated()
//                .antMatchers( "/orders/order/**").hasAuthority("ADMIN")
//                .and().httpBasic(Customizer.withDefaults()).build();





        return http.build();




    }


}

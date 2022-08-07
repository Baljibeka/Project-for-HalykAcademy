package com.company.FinalProject.configuration;

import com.company.FinalProject.entity.User;
import com.company.FinalProject.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with login " + username + " not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(foundUser.getLogin())
                .password(foundUser.getPassword())
                .authorities(String.valueOf(foundUser.getRole()))
                .build();
    }
}

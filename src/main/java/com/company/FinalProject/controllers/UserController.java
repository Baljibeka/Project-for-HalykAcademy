package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.User.UserDTO;
import com.company.FinalProject.dto.User.UserResponseDTO;
import com.company.FinalProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping
    public List<UserDTO> getAll(){
        return userService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("user")
    public void create(@RequestBody UserResponseDTO userResponseDTO){
       userService.create(userResponseDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/user")
    public void update(@RequestBody UserResponseDTO userResponseDTO){
        userService.update(userResponseDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/user/{userID}")
    public void delete(@PathVariable("userID") long id){
        userService.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/{userID}")
    public Optional<UserDTO> findByID(@PathVariable("userID") long id){
        return userService.findByID(id);
    }
}

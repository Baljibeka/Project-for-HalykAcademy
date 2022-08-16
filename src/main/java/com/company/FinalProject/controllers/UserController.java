package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.User.UserAdminDTO;
import com.company.FinalProject.dto.User.UserDTO;
import com.company.FinalProject.dto.User.UserFullDTO;
import com.company.FinalProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PreAuthorize("permitAll()")
    @PostMapping("user")
    public void create(@RequestBody UserFullDTO userFullDTO){
       userService.create(userFullDTO);
    }
    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/user")
    public void update(@RequestBody UserFullDTO userFullDTO){
        userService.update(userFullDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/user/admin")
    public void adminUpdate(@RequestBody UserAdminDTO userAdminDTO){
        userService.adminUpdate(userAdminDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/user/{userID}")
    public void delete(@PathVariable("userID") long id){
        userService.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/{userID}")
    public UserDTO findByID(@PathVariable("userID") long id){
        return userService.findByID(id);
    }
}

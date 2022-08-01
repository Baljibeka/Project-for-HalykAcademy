package com.company.FinalProject.controllers;

import com.company.FinalProject.dto.User.UserDTO;
import com.company.FinalProject.dto.User.UserResponseDTO;
import com.company.FinalProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/user")
    private List<UserDTO> getAll(){
        return userService.getAll();
    }
    @PostMapping("user")
    private UserResponseDTO create(@RequestBody UserResponseDTO userResponseDTO){
        return userService.create(userResponseDTO);
    }
    @PutMapping("/user/{userID}")
    private void update(@RequestBody UserResponseDTO userResponseDTO,@PathVariable("userID") long id){
        if(!Objects.equals(id, userResponseDTO.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
        userService.update(userResponseDTO, id);
    }
    @DeleteMapping("/user/{userID}")
    private void delete(@PathVariable("userID") long id){
        userService.delete(id);
    }
    @GetMapping("/user/{userID}")
    private Optional<UserDTO> findByID(@PathVariable("userID") long id){
        return userService.findByID(id);
    }
}

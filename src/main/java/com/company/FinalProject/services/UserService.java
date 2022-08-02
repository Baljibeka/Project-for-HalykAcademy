package com.company.FinalProject.services;

import com.company.FinalProject.dto.User.UserDTO;
import com.company.FinalProject.dto.User.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAll();
    UserResponseDTO create(UserResponseDTO userResponseDTO);
    void update(UserResponseDTO userResponseDTO);
    void delete(long id);
    Optional<UserDTO> findByID(long id);
}

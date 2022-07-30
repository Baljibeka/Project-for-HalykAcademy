package com.company.FinalProject.services;

import com.company.FinalProject.dto.UserDTO;
import com.company.FinalProject.dto.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAll();
    UserResponseDTO create(UserResponseDTO userResponseDTO);
    void update(UserResponseDTO userResponseDTO, long id);
    void delete(long id);
    Optional<UserDTO> findByID(long id);
}

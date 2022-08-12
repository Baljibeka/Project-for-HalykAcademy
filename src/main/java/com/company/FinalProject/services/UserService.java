package com.company.FinalProject.services;

import com.company.FinalProject.dto.User.UserAdminDTO;
import com.company.FinalProject.dto.User.UserDTO;
import com.company.FinalProject.dto.User.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAll();
    void create(UserResponseDTO userResponseDTO);
    void update(UserResponseDTO userResponseDTO);
    void delete(long id);
    UserDTO findByID(long id);
    void adminUpdate(UserAdminDTO userAdminDTO);
}

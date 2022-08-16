package com.company.FinalProject.services;

import com.company.FinalProject.dto.User.UserAdminDTO;
import com.company.FinalProject.dto.User.UserDTO;
import com.company.FinalProject.dto.User.UserFullDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAll();
    void create(UserFullDTO userFullDTO);
    void update(UserFullDTO userFullDTO);
    void delete(long id);
    UserDTO findByID(long id);
    void adminUpdate(UserAdminDTO userAdminDTO);
}

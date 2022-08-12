package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.User.UserAdminDTO;
import com.company.FinalProject.dto.User.UserDTO;
import com.company.FinalProject.dto.User.UserResponseDTO;
import com.company.FinalProject.entity.User;
import com.company.FinalProject.repo.UserRepository;
import com.company.FinalProject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(User::convertToDTO).toList();
    }

    @Override
    public void create(UserResponseDTO userResponseDTO) {
        userRepository.saveAndFlush(
                new User(
                        userResponseDTO.getId(),
                        userResponseDTO.getLogin(),
                        passwordEncoder.encode(userResponseDTO.getPassword())
                )
        );
    }

    @Override
    public void update(UserResponseDTO userResponseDTO) {
        User user = userRepository.findById(userResponseDTO.getId()).orElseThrow();
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), user.getLogin())) {
            userRepository.save(new User(
                    userResponseDTO.getId(),
                    userResponseDTO.getLogin(),
                    passwordEncoder.encode(userResponseDTO.getPassword()),
                    user.getRole(), user.getIsBlocked()
            ));
        }
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO findByID(long id) {
        return userRepository.findById(id).map(User::convertToDTO).orElseThrow();
    }

    @Override
    public void adminUpdate(UserAdminDTO userAdminDTO) {
        User user = userRepository.findById(userAdminDTO.getId()).orElseThrow();
            userRepository.save(new User(
                    userAdminDTO.getId(),
                    user.getLogin(), user.getPassword(),
                    userAdminDTO.getRole(),
                    userAdminDTO.getIsBlocked()
            ));
        }
}

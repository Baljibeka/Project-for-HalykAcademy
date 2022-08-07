package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.User.UserDTO;
import com.company.FinalProject.dto.User.UserResponseDTO;
import com.company.FinalProject.entity.User;
import com.company.FinalProject.repo.UserRepository;
import com.company.FinalProject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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
                        null,
                        userResponseDTO.getLogin(),
                        passwordEncoder.encode(userResponseDTO.getPassword())
                )
        );
    }

    @Override
    public void update(UserResponseDTO userResponseDTO) {
        User user=userResponseDTO.convertToEntity();
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDTO> findByID(long id) {
        return userRepository.findById(id).map(User::convertToDTO);
    }
}

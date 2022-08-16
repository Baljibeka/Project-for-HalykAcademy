package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.User.UserAdminDTO;
import com.company.FinalProject.dto.User.UserDTO;
import com.company.FinalProject.dto.User.UserFullDTO;
import com.company.FinalProject.entity.User;
import com.company.FinalProject.entity.UserRole;
import com.company.FinalProject.exception.NotFoundException;
import com.company.FinalProject.exception.UserIsNotAllowedException;
import com.company.FinalProject.repo.UserRepository;
import com.company.FinalProject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public void create(UserFullDTO userFullDTO) {
        userRepository.saveAndFlush(
                new User(
                        userFullDTO.getId(),
                        userFullDTO.getLogin(),
                        passwordEncoder.encode(userFullDTO.getPassword()),
                        UserRole.USER,
                        false
                )
        );
    }

    @Override
    public void update(UserFullDTO userFullDTO) {
        User user = userRepository.findById(userFullDTO.getId()).orElseThrow(()->new NotFoundException("There is no such user"));
        if (Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), user.getLogin())) {
            userRepository.save(new User(
                    userFullDTO.getId(),
                    userFullDTO.getLogin(),
                    passwordEncoder.encode(userFullDTO.getPassword()),
                    user.getRole(), user.getIsBlocked()
            ));
        }
        else throw new UserIsNotAllowedException("It's not your account");
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO findByID(long id) {
        return userRepository.findById(id).map(User::convertToDTO).orElseThrow(()->new NotFoundException("There is no such user"));
    }

    @Override
    public void adminUpdate(UserAdminDTO userAdminDTO) {
        User user = userRepository.findById(userAdminDTO.getId()).orElseThrow(()-> new NotFoundException("There is no user"));
            userRepository.save(new User(
                    userAdminDTO.getId(),
                    user.getLogin(), user.getPassword(),
                    userAdminDTO.getRole(),
                    userAdminDTO.getIsBlocked()
            ));
    }
}

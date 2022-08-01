package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.User.UserDTO;
import com.company.FinalProject.dto.User.UserResponseDTO;
import com.company.FinalProject.entity.User;
import com.company.FinalProject.repo.UserRepository;
import com.company.FinalProject.services.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(User::convertToDTO).toList();
    }

    @Override
    public UserResponseDTO create(UserResponseDTO userResponseDTO) {
        User user=userResponseDTO.convertToEntity();
        return userRepository.save(user).convertToResponseDTO();
    }

    @Override
    public void update(UserResponseDTO userResponseDTO, long id) {
        User user=userResponseDTO.convertToEntity();
        User existingUser=null;
        try {
            existingUser = userRepository.findById(user.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingUser.setLogin(user.getLogin());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            userRepository.save(user);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
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

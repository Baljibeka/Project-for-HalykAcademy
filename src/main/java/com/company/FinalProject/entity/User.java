package com.company.FinalProject.entity;

import com.company.FinalProject.dto.User.UserDTO;
import com.company.FinalProject.dto.User.UserResponseDTO;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="users")
public class User {
    @Id
    @SequenceGenerator(
        name="user_sequence",
        sequenceName="user_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_sequence"
    )
    @Column(name="user_id")
    private long id;
    @Column(unique = true, name="login")
    private String login;
    @Column(name="password")
    private String password;
    @Column(name="role")
    private UserRole role;
    @Column(name="status")
    private Boolean isBlocked;

    public User(long id, String login, String password, UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }


    public UserDTO convertToDTO(){
        UserDTO userDTO=new UserDTO();
        userDTO.setId(this.getId());
        userDTO.setLogin(this.getLogin());
        userDTO.setRole(this.getRole());
        return userDTO;
    }
    public UserResponseDTO convertToResponseDTO(){
        UserResponseDTO userResponseDTO=new UserResponseDTO();
        userResponseDTO.setId(this.getId());
        userResponseDTO.setPassword(this.getPassword());
        userResponseDTO.setLogin(this.getLogin());
        userResponseDTO.setRole(this.getRole());
        return userResponseDTO;
    }
}

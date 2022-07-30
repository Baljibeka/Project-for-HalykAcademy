package com.company.FinalProject.entity;

import com.company.FinalProject.dto.UserDTO;
import com.company.FinalProject.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private Boolean status;

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

package com.company.FinalProject.dto;

import com.company.FinalProject.entity.User;
import com.company.FinalProject.entity.UserRole;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseDTO {
    private long id;
    private String login;
    private String password;
    private UserRole role;

    public User convertToEntity(){
        User user=new User();
        user.setId(this.getId());
        user.setLogin(this.getLogin());
        user.setPassword(this.getPassword());
        user.setRole(this.getRole());
        return user;
    }
}

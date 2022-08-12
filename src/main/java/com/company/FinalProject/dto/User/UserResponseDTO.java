package com.company.FinalProject.dto.User;

import com.company.FinalProject.entity.User;
import com.company.FinalProject.entity.UserRole;
import lombok.*;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private long id;
    private String login;
    private String password;

    public User convertToEntity(){
        User user=new User();
        user.setId(this.getId());
        user.setLogin(this.getLogin());
        user.setPassword(this.getPassword());
        return user;
    }
}

package com.company.FinalProject.dto.User;

import com.company.FinalProject.entity.User;
import com.company.FinalProject.entity.UserRole;
import lombok.*;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private long id;
    private String login;
    private UserRole role;

    public User convertToEntity(){
        User user = new User();
        user.setId(this.getId());
        user.setLogin(this.getLogin());
        user.setRole(this.getRole());
        return user;
    }

}

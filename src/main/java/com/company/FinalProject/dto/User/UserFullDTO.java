package com.company.FinalProject.dto.User;

import com.company.FinalProject.entity.User;
import com.company.FinalProject.entity.UserRole;
import lombok.*;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserFullDTO {
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

package com.company.FinalProject.dto.User;

import com.company.FinalProject.entity.User;
import com.company.FinalProject.entity.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserAdminDTO {
    private long id;
    private UserRole role;
    private Boolean isBlocked;

    public User convertToEntity(){
        User user = new User();
        user.setId(this.getId());
        user.setRole(this.getRole());
        user.setIsBlocked(this.getIsBlocked());
        return  user;
    }
}

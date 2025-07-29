package com.ra.ss16baitap.model.dto.request;

import com.ra.ss16baitap.model.entity.Role;
import com.ra.ss16baitap.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO{
    private Long id;
    private String username;
    private Role role;

    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getRole());
    }
}

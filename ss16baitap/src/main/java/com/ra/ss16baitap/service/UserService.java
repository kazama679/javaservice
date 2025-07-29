package com.ra.ss16baitap.service;

import com.ra.ss16baitap.model.entity.Role;
import com.ra.ss16baitap.model.entity.User;

public interface UserService {
    User getCurrentUser();
    User updateUserRole(Long userId, Role newRole);
}

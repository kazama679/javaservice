package com.ra.ss15baitap.service;

import com.ra.ss15baitap.model.entity.Staff;

public interface StaffService {
    Staff create(Staff staff);
    Staff assignRole(Long id, Staff.Role role);
    Staff getMyInfo(String email);
    Staff updateMyInfo(String email, Staff update);
}

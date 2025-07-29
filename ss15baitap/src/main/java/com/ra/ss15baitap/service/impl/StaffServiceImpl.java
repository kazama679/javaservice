package com.ra.ss15baitap.service.impl;

import com.ra.ss15baitap.model.entity.Staff;
import com.ra.ss15baitap.repository.StaffRepository;
import com.ra.ss15baitap.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;

    public Staff create(Staff staff) {
        staff.setStatus(Staff.Status.ACTIVE);
        return staffRepository.save(staff);
    }

    public Staff assignRole(Long id, Staff.Role role) {
        Staff staff = staffRepository.findById(id).orElseThrow();
        staff.setRole(role);
        return staffRepository.save(staff);
    }

    public Staff getMyInfo(String email) {
        return staffRepository.findByEmail(email).orElseThrow();
    }

    public Staff updateMyInfo(String email, Staff update) {
        Staff staff = getMyInfo(email);
        staff.setName(update.getName());
        staff.setPhone(update.getPhone());
        return staffRepository.save(staff);
    }
}

package com.ra.ss12.service;

import com.ra.ss12.model.entity.UserBai3;
import com.ra.ss12.repository.UserBai3Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceBai3Impl implements UserDetailsService {

    @Autowired
    private UserBai3Repository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBai3 user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng: " + username));
        return UserPrincipalBai3.fromUser(user);
    }
}

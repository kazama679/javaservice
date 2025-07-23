package com.ra.ss12.service;

import com.ra.ss12.model.entity.AppUser;
import com.ra.ss12.model.entity.Role;
import com.ra.ss12.security.AppUserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("admin")) {
            throw new UsernameNotFoundException("User not found");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1, "ROLE_ADMIN"));

        AppUser user = new AppUser(1L, "admin", "$2a$10$3F3uTKdZ0bhF9EHOxlve6uPYFqHzdqbXoL1j1iKD0Yw3Xh2Eog7pa", true, roles);

        return new AppUserPrincipal(user);
    }
}

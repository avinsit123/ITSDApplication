package com.io.itsduser.service;

import com.io.itsduser.model.User;
import com.io.itsduser.model.types.SecurityUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public SecurityUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.getUserByUsername(username);
            // todo: remove trim after db migration
            return new SecurityUserDetails(user.getName().trim(), user.getPassword().trim(), user.getRole().trim());
        } catch (Exception e) {
            throw  new UsernameNotFoundException("User name not found", e);
        }
    }
}

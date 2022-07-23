package com.io.login;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUtils {

    public static UserDetails getLoggedInUser() throws ClassCastException {
        final Object userObject = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(userObject instanceof UserDetails)
            return (UserDetails) userObject;
        else
            throw new ClassCastException("Logged-in user not of type user details");
    }
}

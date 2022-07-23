package com.io.login;

import com.io.itsduser.model.User;
import com.io.itsduser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String getLoginForm() {
        return "login";
    }

    @GetMapping(value = "/homepage")
    public String getHomepageWithAllDetails() {
        return "homepage";
    }

    @GetMapping(value = "/unauthorized-access")
    public String getLoginErrorPage() {
        return "403";
    }

    @GetMapping(value = "/profile")
    public String getProfile(Model model) {
        UserDetails currentUserDetails = LoginUtils.getLoggedInUser();
        User currentUser = userService.getUserByUsername(currentUserDetails.getUsername());
        model.addAttribute("userProfile", currentUser);
        return "profile";
    }
}

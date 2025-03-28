package com.example.Library.controller;

import com.example.Library.entity.User;
import com.example.Library.service.AuthService;
import com.example.Library.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
    private final AuthService authService;
    private final UserService userService;
    public UserController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllUsers(Model model) {
        model.addAttribute("allusers", userService.getAllUsers());
        return "user/users";
    }

    @GetMapping("/sing-up")
    public String addUser(User user , Model model) {
        model.addAttribute("user", user);
        return "user/sing-up";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user ) {
        userService.saveUserByJdbc(user);
        return "home";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
       User user = userService.getUserById(id);
        userService.deleteUserByJdbc(user);
        return "home";
    }

    @GetMapping("/login")
    public String loginForm(User user, Model model) {
    model.addAttribute("user", user);
    return "user/login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        authService.login(username);
        return "home";
    }


    @GetMapping("/profile")
    public String profileForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "user/profile";
    }

}

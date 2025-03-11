package com.example.Library.controller;

import com.example.Library.entity.User;
import com.example.Library.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
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
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "home";
    }

    @GetMapping("/login")
    public String loginForm(User user, Model model) {
    model.addAttribute("user", user);
    return "user/login";
    }

}

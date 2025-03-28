package com.example.Library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class LibraryController {
public boolean login = false;


    @GetMapping("/")
    public String home() {
        return "home";
    }

}

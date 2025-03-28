package com.example.Library.controller;


import com.example.Library.entity.BorrowRecord;
import com.example.Library.service.BorrowService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BorrowRecordController {
    private final BorrowService borrowService;

    public BorrowRecordController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }


    @GetMapping("/borrow/{id}")
    public String borrowBook(@PathVariable(value = "id") Long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        borrowService.getBookForUser(bookId ,username);
        return "/home";
    }

    @GetMapping("/returnbook")
    public String returnBook(BorrowRecord borrowRecord , Model model) {
        model.addAttribute("borrowRecord" , borrowRecord);
        return "book/return-book";
    }

    @GetMapping("/takenbooks")
    public String takenBooks(Model model) {
        model.addAttribute("taken_book" , borrowService.getTakenBooks());
        return "book/return-book";
    }

    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable Long id) {
        borrowService.returnBook(id);
        return "redirect:/takenbooks";
    }

    @GetMapping("/user/{userId}")
    public List<BorrowRecord> getBorrowRecordsByUser(@PathVariable Long userId) {
        return borrowService.getBorrowRecordsByUserId(userId);
    }

}
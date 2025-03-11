package com.example.Library.controller;

import com.example.Library.entity.Book;
import com.example.Library.entity.BorrowRecord;
import com.example.Library.entity.User;
import com.example.Library.repository.BorrowRecordRepository;
import com.example.Library.service.BorrowService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BorrowRecordController {

    private final BorrowService borrowService;

    public BorrowRecordController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping
    public String borrowBook(Model model , Book book , User user) {
        model.addAttribute("book", book);
        model.addAttribute("user", user);
        borrowService.getBookForUser(user , book);
        return "borrowRecord";
    }

    @PostMapping
    public String returnBook(Model model , BorrowRecord borrowRecord) {
        model.addAttribute("book", borrowRecord.getBook());
        model.addAttribute("user", borrowRecord.getUser());
        borrowService.returnBook(borrowRecord);
        return "borrowRecord";
    }

}

package com.example.Library.controller;

import com.example.Library.entity.Book;
import com.example.Library.entity.BorrowRecord;
import com.example.Library.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
public class BookController {

   private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String books(Model model ,
                        @RequestParam(value = "page", required = false , defaultValue = "1") int page,
                        @RequestParam(value = "limit", required = false , defaultValue = "10") int limit) {
        model.addAttribute("availablebooks" , bookService.getAvailableBooks(page, limit));
        model.addAttribute("page", page);
        int totalPages = (int)Math.ceil(1.0 * bookService.getAllCount() / limit);
        if(totalPages > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("page_numbers", pageNumbers);
        }
        return "book/books";
    }

    @GetMapping("/addbook")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addBook(Book book , Model model) {
        model.addAttribute("book" , book);
        return "book/new-book";
    }


    @PostMapping("/savebook")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/deleteBook/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable(value = "id") Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/updateBook{id}")
    public String updateBook(@PathVariable(value = "id") Long id , Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book" , book);
        return "book/update-book";
    }
}

package com.example.Library.controller;

import com.example.Library.entity.Book;
import com.example.Library.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BookController {

   private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("allbooks" , bookService.getAll());
        return "book/books";
    }

    @GetMapping("/addbook")
    public String addBook(Book book , Model model) {
        model.addAttribute("book" , book);
        return "book/new-book";
    }

    @PostMapping("/savebook")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/deleteBook/{id}")
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

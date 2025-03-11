package com.example.Library.service;

import com.example.Library.entity.Book;
import com.example.Library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public List<Book> getAvailableBooks() {
       return bookRepository.findByAvailableTrue();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        Book book;
        if (optionalBook.isPresent()) {
            book = optionalBook.get();
        }
        else {
            throw new RuntimeException("Book not found");
        }
        return book;
    }
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}

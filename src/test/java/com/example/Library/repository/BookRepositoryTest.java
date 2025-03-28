package com.example.Library.repository;

import com.example.Library.annotacion.Context;
import com.example.Library.entity.Book;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Context
class BookRepositoryTest {

    private final BookRepository bookRepository;

    public BookRepositoryTest(BookRepository bookRepository) {
        this.bookRepository  = bookRepository;
    }

    @Test
    void shouldFindBookByAvailableFalse_whenValidAvailableFalse() {
        List<Book> books = bookRepository.findByAvailableFalse();

        assertFalse(books.isEmpty() , "Books should not be empty");
    }

    @Test
    void testFindByTitleContaining_whenTitleContainsSubstring_ReturnsNonEmptyList() {
        List<Book> books = bookRepository.findByTitleContaining("Дама");
        books.forEach(System.out::println);

        assertFalse(books.isEmpty() , "Books should not be empty");
    }
}
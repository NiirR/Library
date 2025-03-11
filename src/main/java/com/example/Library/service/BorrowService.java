package com.example.Library.service;

import com.example.Library.entity.Book;
import com.example.Library.entity.BorrowRecord;
import com.example.Library.entity.User;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.BorrowRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BorrowService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;

    public BorrowService(BookRepository bookRepository, BorrowRecordRepository borrowRecordRepository) {
        this.bookRepository = bookRepository;
        this.borrowRecordRepository = borrowRecordRepository;
    }

    public BorrowRecord getBookForUser(User user, Book book) {
        BorrowRecord borrowRecord = new BorrowRecord();
        book.setAvailable(false);
        borrowRecord.setUser(user);
        borrowRecord.setBook(book);
        borrowRecord.setBorrowDate(LocalDate.now());
        return borrowRecordRepository.save(borrowRecord);
    }

    public void returnBook(BorrowRecord borrowRecord) {
        borrowRecord = borrowRecordRepository.findById(borrowRecord.getId())
                .orElseThrow(() -> new RuntimeException("Запись о выдаче книги не найдена"));
        borrowRecord.setReturnDate(LocalDate.now());
        borrowRecordRepository.save(borrowRecord);
    }

    public BorrowRecord getBorrowRecordById(Long id) {
        Optional<BorrowRecord> optionalBorrowRecord = borrowRecordRepository.findById(id);
        BorrowRecord borrowRecord;
        if (optionalBorrowRecord.isPresent()) {
            borrowRecord = optionalBorrowRecord.get();
        }
        else {throw new RuntimeException("Record not found");
        }
        return borrowRecord;
    }

}

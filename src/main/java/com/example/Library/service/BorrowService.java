package com.example.Library.service;

import com.example.Library.entity.Book;
import com.example.Library.entity.BorrowRecord;
import com.example.Library.entity.User;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.BorrowRecordRepository;
import com.example.Library.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BorrowService(BookRepository bookRepository, BorrowRecordRepository borrowRecordRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.borrowRecordRepository = borrowRecordRepository;
        this.userRepository = userRepository;
    }

    public List<BorrowRecord> getTakenBooks() {
        return borrowRecordRepository.findBorrowRecordByReturnDateIsNull();
    }

    @Transactional
    public BorrowRecord getBookForUser(Long bookId , String username) {
        BorrowRecord borrowRecord = new BorrowRecord();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Книга не найдена"));
        book.setAvailable(false);
        borrowRecord.setUser(user);
        borrowRecord.setBook(book);
        borrowRecord.setBorrowDate(LocalDate.now());
        return borrowRecordRepository.save(borrowRecord);
    }

    @Transactional
    public void returnBook(Long borrowRecordId) {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowRecordId)
                .orElseThrow(() -> new RuntimeException("Запись о выдаче книги не найдена"));
        borrowRecord.setReturnDate(LocalDate.now());
        Book book = borrowRecord.getBook();
        book.setAvailable(true);
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

    public List<BorrowRecord> getBorrowRecordsByUser(User user) {
        return borrowRecordRepository.findBorrowRecordByUser(user);
    }

    public List<BorrowRecord>  getBorrowRecordsByUserId(Long userId) {
        return borrowRecordRepository.findBorrowRecordByUserId(userId);
    }

}

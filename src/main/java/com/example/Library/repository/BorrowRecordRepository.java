package com.example.Library.repository;

import com.example.Library.entity.BorrowRecord;
import com.example.Library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    List<BorrowRecord> findBorrowRecordByUser(User user);

    List<BorrowRecord> findBorrowRecordByUserId(Long userId);

    List<BorrowRecord> findBorrowRecordByReturnDateIsNull();
}

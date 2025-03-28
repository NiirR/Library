package com.example.Library.repository;

import com.example.Library.annotacion.Context;
import com.example.Library.entity.BorrowRecord;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Context
class BorrowRecordRepositoryTest {

    private final BorrowRecordRepository borrowRecordRepository;

    BorrowRecordRepositoryTest(BorrowRecordRepository borrowRecordRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
    }

    @Test
    void testFindBorrowRecordByUserId_whenUserHasBorrowedBook_ReturnsNotEmptyList() {
        List<BorrowRecord> borrowRecords = borrowRecordRepository.findBorrowRecordByUserId(26L);

        assertFalse(borrowRecords.isEmpty(), "borrowRecords should not be empty");
    }
}
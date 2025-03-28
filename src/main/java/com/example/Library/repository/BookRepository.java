package com.example.Library.repository;

import com.example.Library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAvailableFalse();

    List<Book> findByTitleContaining(String title);

    @Query("SELECT b FROM Book b WHERE b.available = true ORDER BY b.title OFFSET :offset ROWS\n" +
            "FETCH NEXT :limit ROWS ONLY ")
    List<Book> findByAvailableTrue(@Param("offset") int offset ,@Param("limit") int limit);
}

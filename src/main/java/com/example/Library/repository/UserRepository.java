package com.example.Library.repository;

import com.example.Library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<Long> findUserIdByUsername(String username);
}

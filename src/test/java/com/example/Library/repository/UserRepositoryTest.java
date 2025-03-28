package com.example.Library.repository;

import com.example.Library.annotacion.Context;
import com.example.Library.entity.User;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Context
class UserRepositoryTest {

    private final UserRepository userRepository;


    UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void testFindByUsername_whenUserDoesNotExist_returnsEmpty() {
        Optional<User> user = userRepository.findByUsername("UserTest");

        assertTrue(user.isEmpty());
    }

    @Test
   void testFindByUsername_WhenUserExists_ReturnsUser() {
        Optional<User> user = userRepository.findByUsername("user");

        assertTrue(user.isPresent());
    }
}
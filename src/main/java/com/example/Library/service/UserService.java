package com.example.Library.service;

import com.example.Library.entity.User;
import com.example.Library.repository.JdbcRepository;
import com.example.Library.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JdbcRepository jdbcRepository;


    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, JdbcRepository jdbcRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jdbcRepository = jdbcRepository;
    }


    public List<User>getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(LocalDate.now());
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.orElse(null);
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        else {
            throw new  RuntimeException("User not found");
        }
        return user;
    }

    public void saveUserByJdbc(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(LocalDate.now());
        jdbcRepository.saveUser(user);
    }

    public int deleteUserByJdbc(User user) {
        return jdbcRepository.deleteUser(user);
    }



}

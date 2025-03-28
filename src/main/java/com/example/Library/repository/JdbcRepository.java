package com.example.Library.repository;

import com.example.Library.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int deleteUser(User user) {
        String query = "DELETE FROM user WHERE id = '" + user.getId() + "'";
        return jdbcTemplate.update(query);
    }

    public int saveUser(User user) {
        String query = "INSERT INTO user (username, password, first_name, last_name , email , city , registration_date , role)" +
                " VALUES ('"+ user.getUsername() + "','" + user.getPassword()  + "','" + user.getFirstName()
                + "','" + user.getLastName() + "','" + user.getEmail() + "','" + user.getCity() +  "','" + user.getRegistrationDate() +  "','" + user.getRole() +"')";
        return jdbcTemplate.update(query);
    }
}

package com.example.Library.service;

import com.example.Library.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void givenUser_whenSave_thenValidateUserName() {
        User user = new User("roman" , "1","Roman"
                ,"Gromov" , "roman@gmail.com");

        User saveUser = userService.saveUser(user);
        assertThat(saveUser.getFirstName()).isEqualTo("Roman");
    }

    @Test
    void givenUser_whenSave_thenUserCountIncreasesAfterRegistration() {
        User user = new User("roman" , "1","Roman"
                ,"Gromov" , "roman@gmail.com");
        int allUsers = userService.getAllUsers().size();
        userService.saveUser(user);

        assertThat(userService.getAllUsers().size() == allUsers + 1);
    }
}
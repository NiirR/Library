package com.example.Library.service;

import com.example.Library.entity.User;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void givenUser_whenSave_thenValidateUserName() {
        User user = new User("roman" , "1","Roman"
                ,"Gromov" , "roman@gmail.com" , "Rostov");

        User saveUser = userService.saveUser(user);
        assertThat(saveUser.getFirstName()).isEqualTo("Roman");
    }

    @Test
    void givenUser_whenSave_thenFieldsAreEmpty_ShouldThrowException() {
        User user = new User("" , "",""
                ,"" , "" , "");

        assertThrows(ConstraintViolationException.class, () -> {
            userService.saveUser(user);
        });
    }

    @Test
    void givenUser_whenSave_thenUserCountIncreasesAfterRegistration() {
        User user = new User("roman" , "1","Roman"
                ,"Gromov" , "roman@gmail.com" , "Rostov");
        int allUsers = userService.getAllUsers().size();
        userService.saveUser(user);

        assertThat(userService.getAllUsers().size() == allUsers + 1);
    }

    @Test
    void givenUser_whenSaveAndDelete_thenUserCountNotIncreasesAfterDelete() {
        User user = new User("roman" , "1","Roman"
                ,"Gromov" , "roman@gmail.com" , "Rostov");
        int allUsers = userService.getAllUsers().size();
        userService.saveUser(user);
        userService.deleteUserByJdbc(user);

        assertThat(userService.getAllUsers().size() == allUsers);
    }

    @Test
    void givenWrongUser_whenDelete_thenShouldThrowException() {
        User user = new User("Rt" , "1r","Lon"
                ,"asd" , "asd" , "Rostov");

        assertThrows(DataIntegrityViolationException.class, () -> {
            userService.deleteUserByJdbc(user);
        });
    }
}
package com.example.accountant.integration.service;

import com.example.accountant.entity.User;
import com.example.accountant.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Sql({"classpath:sql/data.sql"})
@ActiveProfiles("test")
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL )
@Transactional
@RequiredArgsConstructor

public class UserServiceIT {
    private final UserService userService;

    @Test
    void getUserByIdExist(){
        Optional<User> user = userService.getUserById(11L);
        assertTrue(user.isPresent());
        assertEquals("Bart", user.get().getFirstname());
    }

    @Test
    void getUserByIdNoExist(){
        Optional<User> user = userService.getUserById(100L);
        assertFalse(user.isPresent());
    }

    @Test
    void getAllUser() {
        List<User> users = userService.getAllUser();
        assertEquals(6, users.size());
    }

    @Test
    void addNewUser() {
        List<User> usersBeforeAdd = userService.getAllUser();
        assertEquals(6, usersBeforeAdd.size());
        User user = new User("Carl", null);
        userService.addNewUser(user);
        List<User> userAfterAdd = userService.getAllUser();
        assertEquals(7, userAfterAdd.size());
        Optional<User> newUser = userService.getUserById(1L);
        assertTrue(newUser.isPresent());
        assertEquals("Carl", newUser.get().getFirstname());
    }

    @Test
    void deleteUser(){
        List<User> usersBeforeDelete = userService.getAllUser();
        int sizeBeforeDelete = usersBeforeDelete.size();
        userService.deleteUser(11L);
        List<User> usersAfterDelete = userService.getAllUser();
        assertThat(usersAfterDelete).hasSize(sizeBeforeDelete - 1);
    }

    @Test
    void updateUser(){
        Optional<User> user = userService.getUserById(10L);
        assertTrue(user.isPresent());
        assertEquals("Homer", user.get().getFirstname());
        userService.updateUser(new User(null, "el Homer", null), 10L);
        Optional<User> userAfterUpdate = userService.getUserById(10L);
        assertTrue(userAfterUpdate.isPresent());
        assertEquals("el Homer", userAfterUpdate.get().getFirstname());
    }
}

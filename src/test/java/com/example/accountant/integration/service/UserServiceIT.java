package com.example.accountant.integration.service;

import com.example.accountant.dto.UserCreateUpdateDto;
import com.example.accountant.dto.UserReadDto;
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
    void findByIdExist(){
        Optional<UserReadDto> userReadDto = userService.findById(11L);
        assertTrue(userReadDto.isPresent());
        assertEquals("Bart", userReadDto.get().getFirstname());
    }

    @Test
    void findByIdNoExist(){
        Optional<UserReadDto> userReadDto = userService.findById(100L);
        assertFalse(userReadDto.isPresent());
    }

    @Test
    void findAll() {
        List<UserReadDto> users = userService.findAll();
        assertEquals(6, users.size());
    }

    @Test
    void create() {
        List<UserReadDto> usersBeforeAdd = userService.findAll();
        assertEquals(6, usersBeforeAdd.size());
        UserCreateUpdateDto userCreateUpdateDto = new UserCreateUpdateDto("Carl", null);
        userService.create(userCreateUpdateDto);
        List<UserReadDto> userAfterAdd = userService.findAll();
        assertEquals(7, userAfterAdd.size());
        Optional<UserReadDto> newUser = userService.findById(1L);
        assertTrue(newUser.isPresent());
        assertEquals("Carl", newUser.get().getFirstname());
    }


    @Test
    void delete(){
        List<UserReadDto> usersBeforeDelete = userService.findAll();
        int sizeBeforeDelete = usersBeforeDelete.size();
        userService.delete(11L);
        List<UserReadDto> usersAfterDelete = userService.findAll();
        assertThat(usersAfterDelete).hasSize(sizeBeforeDelete - 1);
    }

    @Test
    void update(){
        Optional<UserReadDto> userReadDto = userService.findById(10L);
        assertTrue(userReadDto.isPresent());
        assertEquals("Homer", userReadDto.get().getFirstname());
        userService.update(10L, new UserCreateUpdateDto("el Homer", null));
        Optional<UserReadDto> userAfterUpdate = userService.findById(10L);
        assertTrue(userAfterUpdate.isPresent());
        assertEquals("el Homer", userAfterUpdate.get().getFirstname());
    }
}

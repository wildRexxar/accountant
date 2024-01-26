package com.example.accountant.service;

import com.example.accountant.entity.User;
import com.example.accountant.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final Long USER_ID_1 = 1L;
    private static final Long USER_ID_2 = 2L;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getAllUser() {
        List<User> users = new ArrayList<>();
        users.add(new User(USER_ID_1, "Homer", null));
        users.add(new User(USER_ID_2, "Abe", null));
        doReturn(users)
                .when(userRepository).findAll();
        List<User> actualResult = userService.getAllUser();
        assertEquals(2, actualResult.size());
    }
}

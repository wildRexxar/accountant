package com.example.accountant.mapper;

import com.example.accountant.dto.UserReadDto;
import com.example.accountant.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {
    @Override
    public UserReadDto map(User user) {

        return new UserReadDto(
                user.getId(),
                user.getFirstname(),
                null
        );
    }
}

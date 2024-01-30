package com.example.accountant.mapper;

import com.example.accountant.dto.UserCreateUpdateDto;
import com.example.accountant.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserCreateUpdateMapper implements Mapper<UserCreateUpdateDto, User>{

    @Override
    public User map(UserCreateUpdateDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public User map(UserCreateUpdateDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    private void copy(UserCreateUpdateDto object, User user){
        user.setFirstname(object.getFirstname());
        user.setWalletId(object.getWalletId());
    }
}

package com.example.accountant.dto;

import com.example.accountant.entity.Wallet;
import lombok.Value;

@Value
public class UserReadDto {
    Long id;
    String firstname;
    Wallet wallet;
}

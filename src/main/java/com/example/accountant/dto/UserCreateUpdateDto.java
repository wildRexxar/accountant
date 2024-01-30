package com.example.accountant.dto;

import lombok.Value;

@Value
public class UserCreateUpdateDto {
    String firstname;
    Long walletId;
}

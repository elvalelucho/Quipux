package com.luismolina.quipux.dtos;

import lombok.Getter;

@Getter
public class RegisterUserDto {
    private String email;
    private String password;
    private String fullName;
}

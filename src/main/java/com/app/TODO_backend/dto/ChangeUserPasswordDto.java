package com.app.TODO_backend.dto;

import lombok.Data;

@Data
public class ChangeUserPasswordDto {
    private String token;
    private String password;
}

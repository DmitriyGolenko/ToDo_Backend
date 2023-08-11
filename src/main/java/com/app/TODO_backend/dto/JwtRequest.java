package com.app.TODO_backend.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}

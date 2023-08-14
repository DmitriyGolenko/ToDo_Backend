package com.app.TODO_backend.dto;

import com.app.TODO_backend.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "User")
public class UserDto {
    private String login;
    private String mail;
    public UserDto(User user){
        this.login = user.getLogin();
        this.mail = user.getMail();
    }
}

package com.app.TODO_backend.controller;

import com.app.TODO_backend.dto.UserDto;
import com.app.TODO_backend.entity.User;
import com.app.TODO_backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/mail")
    @Operation(description = "Возвращает пользователя по почте")
    @ApiResponse(responseCode = "200",description = "User",
            content = @Content(schema = @Schema(implementation = UserDto.class),mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Пользователь с данной почтой не найден")
    public ResponseEntity<?> getUserByMail(@RequestParam String mail){
        Optional<User> user = userService.findByMail(mail);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new UserDto(user.get()));
    }
}

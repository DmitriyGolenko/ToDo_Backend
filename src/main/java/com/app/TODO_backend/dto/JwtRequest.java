package com.app.TODO_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Jwt request")
public class JwtRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}

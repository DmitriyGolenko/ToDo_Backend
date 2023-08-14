package com.app.TODO_backend.entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "task")
@Schema(description = "Task description")
public class Task {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.annotation.Id
    @Hidden
    private Long id;
    @Schema(description = "Test description")
    @NotNull
    private LocalDate date;
    private String description;
    private boolean done = false;

}

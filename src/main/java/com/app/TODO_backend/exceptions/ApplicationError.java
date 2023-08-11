package com.app.TODO_backend.exceptions;

import lombok.Data;

import java.util.Date;


@Data
public class ApplicationError {
    private int status;
    private String message;
    private Date time;

    public ApplicationError(int status, String message){
        this.status = status;
        this.message = message;
        this.time = new Date();
    }
}

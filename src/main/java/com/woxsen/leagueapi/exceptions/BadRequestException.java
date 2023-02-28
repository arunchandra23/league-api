package com.woxsen.leagueapi.exceptions;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestException extends RuntimeException {

    private String message;
    private List<String> errors;

    public BadRequestException(String message) {
        this.message = message;
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}

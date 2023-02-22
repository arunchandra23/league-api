package com.woxsen.leagueapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResourceNotFoundException extends RuntimeException {

    private String message;


    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.woxsen.leagueapi.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Email(message = "Invalid email",regexp = "[a-z0-9._%+-]+@woxsen+\\.edu.in",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;
    @NotBlank(message = "In-valid email")
    private String password;
}

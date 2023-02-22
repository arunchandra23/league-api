package com.woxsen.leagueapi.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@woxsen.edu.in+$",message = "Invalid email")
    private String email;
    @NotBlank(message = "In-valid email")
    private String password;
}

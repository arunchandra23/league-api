package com.woxsen.leagueapi.payload.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotBlank(message = "Invalid first name")
    private String firstName;
    @NotBlank(message = "Invalid last name")
    private String lastName;
    @NotBlank(message = "Invalid username")
    private String userName;
    @Email(message = "Invalid email",regexp = "[a-z0-9._%+-]+@woxsen+\\.edu.in",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;
    @Pattern(regexp = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")
    private String phone;
    @NotBlank(message = "In-valid email")
    private String password;
    @NotNull
    private UUID courseId;

}

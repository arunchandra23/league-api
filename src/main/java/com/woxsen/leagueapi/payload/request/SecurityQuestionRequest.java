package com.woxsen.leagueapi.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecurityQuestionRequest {

    @NotBlank(message = "Question cannot be blank or null")
    private String question;
}

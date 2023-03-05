package com.woxsen.leagueapi.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordResetRequest {

    private String email;
    private String securityAnswer;
    private UUID securityQuestionId;

    private String newPassword;
}


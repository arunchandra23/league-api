package com.woxsen.leagueapi.payload.request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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


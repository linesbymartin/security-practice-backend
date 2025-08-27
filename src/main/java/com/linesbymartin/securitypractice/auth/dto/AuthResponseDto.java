package com.linesbymartin.securitypractice.auth.dto;

import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDto {
    private String tokenType;
    private String accessToken;
    private Instant expiresAt;
}

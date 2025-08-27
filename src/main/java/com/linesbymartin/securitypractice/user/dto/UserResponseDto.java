package com.linesbymartin.securitypractice.user.dto;

import com.linesbymartin.securitypractice.user.domain.UserRole;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private Instant timestampCreate;
    private Instant timestampUpdate;
    private UserRole role;
}

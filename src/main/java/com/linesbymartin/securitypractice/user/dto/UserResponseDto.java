package com.linesbymartin.securitypractice.user.dto;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
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
}

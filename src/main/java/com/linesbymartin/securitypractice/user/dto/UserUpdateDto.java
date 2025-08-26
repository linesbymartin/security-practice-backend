package com.linesbymartin.securitypractice.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDto {

    @NotBlank(message = "First name is required")
    @Size(min = 3, message = "First name must be at least 3 characters long")
    @Size(max = 100, message = "First name must be at most 100 characters long")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 3, message = "Last name must be at least 3 characters long")
    @Size(max = 100, message = "Last name must be at most 100 characters long")
    private String lastName;
}

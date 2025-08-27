package com.linesbymartin.securitypractice.auth.web;

import com.linesbymartin.securitypractice.auth.dto.RegisterRequestDto;
import com.linesbymartin.securitypractice.auth.dto.AuthResponseDto;
import com.linesbymartin.securitypractice.auth.dto.LoginRequestDto;
import com.linesbymartin.securitypractice.auth.service.AuthService;
import com.linesbymartin.securitypractice.user.dto.UserResponseDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto register(
            @RequestBody @Valid RegisterRequestDto registerRequestDto
    ) {
        return authService.register(registerRequestDto);
    }

    @PostMapping("/login")
    public AuthResponseDto login(
            @RequestBody @Valid LoginRequestDto loginRequestDto
    ) {
        return authService.login(loginRequestDto);
    }
}

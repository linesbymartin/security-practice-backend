package com.linesbymartin.securitypractice.auth.service;

import com.linesbymartin.securitypractice.auth.dto.AuthResponseDto;
import com.linesbymartin.securitypractice.auth.dto.LoginRequestDto;
import com.linesbymartin.securitypractice.auth.dto.RegisterRequestDto;
import com.linesbymartin.securitypractice.user.dto.UserResponseDto;

public interface AuthService {
    UserResponseDto register(RegisterRequestDto registerRequestDto);

    AuthResponseDto login(LoginRequestDto loginRequestDto);
}

package com.linesbymartin.securitypractice.auth.service.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.linesbymartin.securitypractice.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import com.linesbymartin.securitypractice.auth.dto.RegisterRequestDto;
import com.linesbymartin.securitypractice.auth.dto.AuthResponseDto;
import com.linesbymartin.securitypractice.auth.dto.LoginRequestDto;
import com.linesbymartin.securitypractice.auth.service.AuthService;
import com.linesbymartin.securitypractice.user.dto.UserResponseDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.linesbymartin.securitypractice.user.domain.UserEntity;
import com.linesbymartin.securitypractice.user.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;
import com.linesbymartin.securitypractice.user.domain.UserRole;
import com.linesbymartin.securitypractice.security.JwtService;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserMapper userMapper, UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @Transactional
    public UserResponseDto register(RegisterRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EntityExistsException("User with email " + dto.getEmail() + " already exists");
        }

        UserEntity entity = userMapper.fromRegister(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setRole(UserRole.USER);
        UserEntity saved = userRepository.save(entity);
        return userMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );

        UserEntity user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.issueToken(user);
        Instant expiresAt = jwtService.getExpiryInstant(token);

        return new AuthResponseDto("Bearer", token, expiresAt);
    }
}

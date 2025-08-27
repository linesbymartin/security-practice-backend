package com.linesbymartin.securitypractice.user.service.impl;

import com.linesbymartin.securitypractice.common.service.impl.CrudServiceImpl;
import com.linesbymartin.securitypractice.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.linesbymartin.securitypractice.user.service.UserService;
import com.linesbymartin.securitypractice.user.dto.UserResponseDto;
import com.linesbymartin.securitypractice.user.domain.UserEntity;
import com.linesbymartin.securitypractice.user.dto.UserCreateDto;
import com.linesbymartin.securitypractice.user.dto.UserUpdateDto;
import com.linesbymartin.securitypractice.user.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;
import com.linesbymartin.securitypractice.ultils.ClaimsUtil;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl extends CrudServiceImpl<UserEntity, UUID, UserResponseDto, UserCreateDto, UserUpdateDto> implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        super(userRepository, userMapper);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserResponseDto create(UserCreateDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())){
            throw new EntityExistsException("User with email " + dto.getEmail() + " already exists");
        }

        UserEntity entity = userMapper.fromCreate(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        UserEntity saved = userRepository.save(entity);
        return userMapper.toResponse(saved);
    }

    @Override
    public UserResponseDto getOwnUser() {
        return super.getById(ClaimsUtil.getCurrentUserId());
    }
}

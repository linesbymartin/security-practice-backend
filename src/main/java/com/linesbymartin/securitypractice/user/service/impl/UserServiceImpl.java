package com.linesbymartin.securitypractice.user.service.impl;

import com.linesbymartin.securitypractice.common.service.impl.CrudServiceImpl;
import com.linesbymartin.securitypractice.user.domain.UserEntity;
import com.linesbymartin.securitypractice.user.dto.UserCreateDto;
import com.linesbymartin.securitypractice.user.dto.UserResponseDto;
import com.linesbymartin.securitypractice.user.dto.UserUpdateDto;
import com.linesbymartin.securitypractice.user.mapper.UserMapper;
import com.linesbymartin.securitypractice.user.repository.UserRepository;
import com.linesbymartin.securitypractice.user.service.UserService;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl extends CrudServiceImpl<UserEntity, UUID, UserResponseDto, UserCreateDto, UserUpdateDto> implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository, userMapper);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto create(UserCreateDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())){
            throw new EntityExistsException("User with email " + dto.getEmail() + " already exists");
        }

        return super.create(dto);
    }

}

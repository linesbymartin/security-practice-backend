package com.linesbymartin.securitypractice.user.service;


import com.linesbymartin.securitypractice.common.service.CrudService;
import com.linesbymartin.securitypractice.user.dto.UserCreateDto;
import com.linesbymartin.securitypractice.user.dto.UserResponseDto;
import com.linesbymartin.securitypractice.user.dto.UserUpdateDto;

import java.util.UUID;

public interface UserService extends CrudService<UserResponseDto, UUID, UserCreateDto, UserUpdateDto> {
}


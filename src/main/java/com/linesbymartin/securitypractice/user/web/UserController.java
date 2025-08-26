package com.linesbymartin.securitypractice.user.web;

import com.linesbymartin.securitypractice.common.web.CrudController;
import com.linesbymartin.securitypractice.user.dto.UserResponseDto;
import com.linesbymartin.securitypractice.user.service.UserService;
import com.linesbymartin.securitypractice.user.dto.UserCreateDto;
import com.linesbymartin.securitypractice.user.dto.UserUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController extends CrudController<UserResponseDto, UUID, UserCreateDto, UserUpdateDto> {

    private final UserService userService;

    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }
}

package com.linesbymartin.securitypractice.user.mapper;

import com.linesbymartin.securitypractice.common.mapper.GenericMapper;
import com.linesbymartin.securitypractice.user.domain.UserEntity;
import com.linesbymartin.securitypractice.user.dto.UserCreateDto;
import com.linesbymartin.securitypractice.user.dto.UserResponseDto;
import com.linesbymartin.securitypractice.user.dto.UserUpdateDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserEntity, UserResponseDto, UserCreateDto, UserUpdateDto> {

    @Override
    @Mapping(target = "id", source = "id")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "timestampCreate", source = "timestampCreate")
    @Mapping(target = "timestampUpdate", source = "timestampUpdate")
    UserResponseDto toResponse(UserEntity entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestampCreate",  ignore = true)
    @Mapping(target = "timestampUpdate",  ignore = true)
    UserEntity fromCreate(UserCreateDto dto);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "timestampCreate",  ignore = true)
    @Mapping(target = "timestampUpdate",  ignore = true)
    void updateEntity(@MappingTarget UserEntity entity, UserUpdateDto dto);

}

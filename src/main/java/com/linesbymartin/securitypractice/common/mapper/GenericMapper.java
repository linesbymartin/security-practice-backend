package com.linesbymartin.securitypractice.common.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface GenericMapper<ENTITY, RESPONSE_DTO, CREATE_DTO, UPDATE_DTO> {
    RESPONSE_DTO toResponse(ENTITY entity);

    ENTITY fromCreate(CREATE_DTO dto);

    void updateEntity(@MappingTarget ENTITY entity, UPDATE_DTO dto);

    default List<RESPONSE_DTO> toResponseList(List<ENTITY> entities) {
        return entities.stream().map(this::toResponse).toList();
    }
}

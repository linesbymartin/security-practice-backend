package com.linesbymartin.securitypractice.common.service;

import java.util.List;

public interface CrudService<RESPONSE_DTO, ID, CREATE_DTO, UPDATE_DTO> {
    List<RESPONSE_DTO> getAll();

    RESPONSE_DTO getById(ID id);

    RESPONSE_DTO create(CREATE_DTO dto);

    RESPONSE_DTO update(ID id, UPDATE_DTO dto);

    void delete(ID id);
}

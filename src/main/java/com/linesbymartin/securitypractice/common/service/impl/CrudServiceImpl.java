package com.linesbymartin.securitypractice.common.service.impl;

import com.linesbymartin.securitypractice.common.mapper.GenericMapper;
import com.linesbymartin.securitypractice.common.repository.BaseRepository;
import com.linesbymartin.securitypractice.common.service.CrudService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class CrudServiceImpl<ENTITY, ID, RESPONSE_DTO, CREATE_DTO, UPDATE_DTO> implements CrudService<RESPONSE_DTO, ID, CREATE_DTO, UPDATE_DTO> {

    private final BaseRepository<ENTITY, ID> repository;
    private final GenericMapper<ENTITY, RESPONSE_DTO, CREATE_DTO, UPDATE_DTO> mapper;

    protected CrudServiceImpl(BaseRepository<ENTITY, ID> repository, GenericMapper<ENTITY, RESPONSE_DTO, CREATE_DTO, UPDATE_DTO> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<RESPONSE_DTO> getAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public RESPONSE_DTO getById(ID id) {
        ENTITY entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Resource not found: " + id));
        return mapper.toResponse(entity);
    }

    public RESPONSE_DTO create(CREATE_DTO dto) {
        ENTITY dbEntity = mapper.fromCreate(dto);
        ENTITY savedEntity = repository.save(dbEntity);
        return mapper.toResponse(savedEntity);
    }

    public RESPONSE_DTO update(ID id, UPDATE_DTO dto) {
        ENTITY dbEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Resource not found: " + id));
        mapper.updateEntity(dbEntity, dto);
        ENTITY saved = repository.save(dbEntity);
        return mapper.toResponse(saved);
    }

    public void delete(ID id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Resource not found: " + id);
        repository.deleteById(id);
    }
}

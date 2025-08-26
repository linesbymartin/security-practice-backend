package com.linesbymartin.securitypractice.common.web;

import com.linesbymartin.securitypractice.common.service.CrudService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class CrudController<RESPONSE_DTO, ID, CREATE_DTO, UPDATE_DTO> {

    private final CrudService<RESPONSE_DTO, ID, CREATE_DTO, UPDATE_DTO> service;

    protected CrudController(CrudService<RESPONSE_DTO, ID, CREATE_DTO, UPDATE_DTO> service) {
        this.service = service;
    }

    @GetMapping
    public List<RESPONSE_DTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RESPONSE_DTO getById(
            @PathVariable ID id
    ) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RESPONSE_DTO create(
            @RequestBody @Valid CREATE_DTO dto
    ) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public RESPONSE_DTO update(
            @PathVariable ID id,
            @RequestBody @Valid UPDATE_DTO dto
    ) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable ID id
    ) {
        service.delete(id);
    }
}

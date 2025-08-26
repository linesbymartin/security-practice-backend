package com.linesbymartin.securitypractice.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Map;

@Getter
@AllArgsConstructor
@Builder
public class ApiErrorDto {

    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> details;
}
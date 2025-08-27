package com.linesbymartin.securitypractice.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import com.linesbymartin.securitypractice.common.dto.ApiErrorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public RestAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ApiErrorDto apiErrorDto = new ApiErrorDto(Instant.now(), httpStatus.value(), httpStatus.getReasonPhrase(), "Authentication required", request.getRequestURI(), Map.of());
        response.setStatus(httpStatus.value());
        response.setContentType("application/json");
        objectMapper.writeValue(response.getOutputStream(), apiErrorDto);
    }
}

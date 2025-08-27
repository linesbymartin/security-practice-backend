package com.linesbymartin.securitypractice.common.web;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.linesbymartin.securitypractice.common.dto.ApiErrorDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.validation.ConstraintViolationException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityExistsException;
import org.springframework.validation.FieldError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<ApiErrorDto> handleEntityNotFoundException(EntityNotFoundException exception, HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, exception.getMessage(), req.getRequestURI(), Map.of());
    }

    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public ResponseEntity<ApiErrorDto> handleAuth(RuntimeException exception, HttpServletRequest req) {
        return build(HttpStatus.UNAUTHORIZED, "Invalid email or password", req.getRequestURI(), Map.of());
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ApiErrorDto> handleEntityExistsException(EntityExistsException exception, HttpServletRequest req) {
        return build(HttpStatus.CONFLICT, exception.getMessage(), req.getRequestURI(), Map.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDto> handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest req) {
        Map<String, Object> details = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return build(HttpStatus.BAD_REQUEST, "Data Validation failed", req.getRequestURI(), details);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorDto> handleConstraint(ConstraintViolationException ex, HttpServletRequest req) {
        Map<String, Object> details = ex.getConstraintViolations().stream()
                .collect(Collectors.groupingBy(v -> v.getPropertyPath().toString(),
                        Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return build(HttpStatus.BAD_REQUEST, "Constraint violation", req.getRequestURI(), details);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ApiErrorDto> handleGenericException(Exception exception, HttpServletRequest req) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), req.getRequestURI(), Map.of());
    }

    private ResponseEntity<ApiErrorDto> build(HttpStatus httpStatus, String message, String path, Map<String, Object> details) {
        ApiErrorDto body = new ApiErrorDto(Instant.now(), httpStatus.value(), httpStatus.getReasonPhrase(), message, path, details);
        return ResponseEntity.status(httpStatus).body(body);
    }
}

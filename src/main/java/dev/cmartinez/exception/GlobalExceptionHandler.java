package dev.cmartinez.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manejar errores de validación (Bean Validation)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );

        String errorMessage = "Errores de validación: " + errors.toString();
        ResponseError responseError = new ResponseError(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            errorMessage,
            request.getRequestURI()
        );

        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    // Manejar errores de integridad de datos (constraints únicos, etc.)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseError> handleDataIntegrityViolation(
            DataIntegrityViolationException ex, HttpServletRequest request) {
        
        ResponseError responseError = new ResponseError(
            HttpStatus.CONFLICT.value(),
            "Conflict",
            "Violación de integridad de datos: " + ex.getMostSpecificCause().getMessage(),
            request.getRequestURI()
        );

        return new ResponseEntity<>(responseError, HttpStatus.CONFLICT);
    }

    // Manejar errores genéricos
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleGlobalException(
            Exception ex, HttpServletRequest request) {
        
        ResponseError responseError = new ResponseError(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            ex.getMessage(),
            request.getRequestURI()
        );

        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

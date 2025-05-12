package com.picpaysimplificado.infra;

import com.picpaysimplificado.dtos.ExceptionsDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionsDTO> threatDuplicateEntry(DataIntegrityViolationException exception){
        ExceptionsDTO exceptionsDTO = new ExceptionsDTO("Usuario ja cadastrado.", "400");
        return ResponseEntity.badRequest().body(exceptionsDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionsDTO> threat404(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionsDTO> threatGeneralExceptions(EntityNotFoundException exception){
        ExceptionsDTO exceptionsDTO = new ExceptionsDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionsDTO);
    }
}

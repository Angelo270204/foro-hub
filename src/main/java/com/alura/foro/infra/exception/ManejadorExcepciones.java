package com.alura.foro.infra.exception;

import com.alura.foro.domain.EntidadNoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejadorExcepciones {
    @ExceptionHandler(EntidadNoEncontradaException.class)
    public ResponseEntity<DatosError> manejarEntidadNoEncontrada(EntidadNoEncontradaException ex){
        var error = new DatosError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}

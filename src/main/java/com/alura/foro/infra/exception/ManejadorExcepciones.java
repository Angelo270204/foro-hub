package com.alura.foro.infra.exception;

import com.alura.foro.domain.EntidadNoEncontradaException;
import com.alura.foro.domain.topico.DatoMinimoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejadorExcepciones {
    @ExceptionHandler(EntidadNoEncontradaException.class)
    public ResponseEntity<DatosError> manejarEntidadNoEncontrada(EntidadNoEncontradaException ex){
        var error = new DatosError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DatoMinimoException.class)
    public ResponseEntity<DatosError> manejarPeticionSinDatos(DatoMinimoException ex){
        var error = new DatosError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DatosError> manejarErrorValidacion(MethodArgumentNotValidException ex){
        var error = ex.getFieldError();
        var mensaje = error != null ? error.getDefaultMessage() : "Error de validación";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DatosError(mensaje));
    }
}

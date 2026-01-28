package com.alura.foro.domain.curso;

public class CursoDuplicadoException extends RuntimeException {
    public CursoDuplicadoException(String message) {
        super(message);
    }
}

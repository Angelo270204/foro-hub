package com.alura.foro.domain.curso.dto;

import com.alura.foro.domain.curso.Curso;

public record DatosCurso(
        Long id,
        String nombre,
        String categoria
) {
    public DatosCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}

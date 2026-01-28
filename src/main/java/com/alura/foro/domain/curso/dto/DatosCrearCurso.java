package com.alura.foro.domain.curso.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosCrearCurso(
        @NotBlank(message = "El nombre del curso es obligatorio")
        String nombre,
        @NotBlank(message = "La categoria es obligatoria")
        String categoria
) {
}

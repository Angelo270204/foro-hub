package com.alura.foro.domain.topico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCrearTopico(
        @NotBlank(message = "El titulo es obligatorio")
        String titulo,

        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,

        @NotNull(message = "El ID del autor es obligatorio")
        Long autor_id,

        @NotNull(message = "El ID del curso es obligatorio")
        Long curso_id
) {
}

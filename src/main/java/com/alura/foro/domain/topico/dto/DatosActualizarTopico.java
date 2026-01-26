package com.alura.foro.domain.topico.dto;

import com.alura.foro.domain.topico.StatusTopico;
import jakarta.validation.constraints.Size;

public record DatosActualizarTopico(
        @Size(max = 100, message = "El título no puede exceder 100 caracteres")
        String titulo,
        
        @Size(max = 255, message = "El mensaje no puede exceder 255 caracteres")
        String mensaje,

        StatusTopico status,
        Long autor_id,
        Long curso_id) {

    public boolean validarMinimoUnDato() {
        return titulo != null || mensaje != null || status != null || autor_id != null || curso_id != null;
    }

}

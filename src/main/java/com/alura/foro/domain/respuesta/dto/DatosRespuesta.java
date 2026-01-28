package com.alura.foro.domain.respuesta.dto;

import com.alura.foro.domain.respuesta.Respuesta;

import java.time.LocalDateTime;

public record DatosRespuesta(
        Long id,
        String mensaje,
        Long idTopico,
        LocalDateTime fechaCreacion,
        Long idUsuario
) {
    public DatosRespuesta(Respuesta res){
        this(res.getId(),res.getMensaje(),res.getTopico().getId(),res.getFechaCreacion(),res.getAutor().getId());
    }
}

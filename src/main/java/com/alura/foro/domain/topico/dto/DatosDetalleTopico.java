package com.alura.foro.domain.topico.dto;

import com.alura.foro.domain.curso.dto.DatosCurso;
import com.alura.foro.domain.topico.StatusTopico;
import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.usuario.dto.DatosUsuario;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        DatosUsuario autor,
        DatosCurso curso
) {
    public DatosDetalleTopico(Topico topico){
        this(topico.getTitulo(),
             topico.getMensaje(),
             topico.getFechaCreacion(),
             topico.getStatus(),
             new DatosUsuario(topico.getAutor()),
             new DatosCurso(topico.getCurso()));
    }
}

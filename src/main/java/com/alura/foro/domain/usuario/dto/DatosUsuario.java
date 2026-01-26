package com.alura.foro.domain.usuario.dto;

import com.alura.foro.domain.usuario.Usuario;

public record DatosUsuario(
        Long id,
        String nombre
) {
    public DatosUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre());
    }
}

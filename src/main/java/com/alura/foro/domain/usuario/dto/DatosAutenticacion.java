package com.alura.foro.domain.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacion(
        @NotBlank(message = "El campo correo no puede estar vacio ni ser null")
        String correoElectronico,
        @NotBlank(message = "El campo contrasena no puede estar vacio ni ser null")
        String contrasena
) {
}

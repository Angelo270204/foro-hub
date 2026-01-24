package com.alura.foro.controller;

import com.alura.foro.domain.topico.TopicoService;
import com.alura.foro.domain.topico.dto.DatosCrearTopico;
import com.alura.foro.domain.topico.dto.DatosRespuestaTopico;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> crearTopico(@RequestBody @Valid DatosCrearTopico datosCrearTopico, UriComponentsBuilder uriBuilder) {
        DatosRespuestaTopico datosRespuesta = topicoService.crearTopico(datosCrearTopico);

        URI url = uriBuilder.path("/topicos/{id}")
                .buildAndExpand(datosRespuesta.id())
                .toUri();

        return ResponseEntity.created(url).body(datosRespuesta);
    }
}

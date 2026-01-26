package com.alura.foro.controller;

import com.alura.foro.domain.topico.TopicoService;
import com.alura.foro.domain.topico.dto.DatosCrearTopico;
import com.alura.foro.domain.topico.dto.DatosDetalleTopico;
import com.alura.foro.domain.topico.dto.DatosRespuestaTopico;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<DatosRespuestaTopico>> listarTopicos(){
        return ResponseEntity.ok(topicoService.obtenerTopicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> obtenerTopicoPorId(@PathVariable Long id){
        DatosDetalleTopico datosRespuesta = topicoService.obtenerTopicoPorId(id);
        return ResponseEntity.ok(datosRespuesta);
    }
}

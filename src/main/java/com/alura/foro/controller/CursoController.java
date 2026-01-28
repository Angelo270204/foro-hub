package com.alura.foro.controller;

import com.alura.foro.domain.curso.CursoService;
import com.alura.foro.domain.curso.dto.DatosCrearCurso;
import com.alura.foro.domain.curso.dto.DatosCurso;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService){
        this.cursoService=cursoService;
    }

    @GetMapping
    public ResponseEntity<List<DatosCurso>> obtenerTodosLosCursos(){
        return ResponseEntity.ok(cursoService.listarCursos());
    }

    @PostMapping
    public ResponseEntity<DatosCurso> crearCurso(@RequestBody @Valid DatosCrearCurso datosCrearCurso, UriComponentsBuilder uriBuilder){
        DatosCurso datos = cursoService.crearCurso(datosCrearCurso);

        URI url = uriBuilder.path("/cursos/{id}")
                .buildAndExpand(datos.id())
                .toUri();

        return ResponseEntity.created(url).body(datos);
    }
}

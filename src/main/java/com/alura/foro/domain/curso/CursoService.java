package com.alura.foro.domain.curso;

import com.alura.foro.domain.curso.dto.DatosCrearCurso;
import com.alura.foro.domain.curso.dto.DatosCurso;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<DatosCurso> listarCursos() {
        return cursoRepository.findAll().stream()
                .map(DatosCurso::new)
                .toList();
    }

    @Transactional
    public DatosCurso crearCurso(DatosCrearCurso curso) {
        if (cursoRepository.existsByNombre(curso.nombre())) {
            throw new CursoDuplicadoException("Ya existe un curso con el nombre: " + curso.nombre());
        }
        Curso datos = cursoRepository.save(new Curso(curso));
        return new DatosCurso(datos);
    }

}

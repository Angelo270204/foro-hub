package com.alura.foro.domain.curso;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    @Transactional
    public Curso crearCurso(Curso curso){
        return cursoRepository.save(curso);
    }
}

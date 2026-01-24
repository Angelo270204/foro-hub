package com.alura.foro.domain.topico;

import com.alura.foro.domain.curso.Curso;
import com.alura.foro.domain.curso.CursoRepository;
import com.alura.foro.domain.topico.dto.DatosCrearTopico;
import com.alura.foro.domain.topico.dto.DatosRespuestaTopico;
import com.alura.foro.domain.usuario.Usuario;
import com.alura.foro.domain.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository; // <-- Agregado
    private final CursoRepository cursoRepository;

    public TopicoService(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    public List<Topico> obtenerTopicos(){
        return topicoRepository.findAll();
    }

    public DatosRespuestaTopico crearTopico(DatosCrearTopico datos) {
        Usuario autor = usuarioRepository.findById(datos.autor_id())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Curso curso = cursoRepository.findById(datos.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        Topico topico = new Topico(datos, autor, curso);

        Topico topicoGuardado = topicoRepository.save(topico);

        return new DatosRespuestaTopico(topicoGuardado);
    }
}

package com.alura.foro.domain.topico;

import com.alura.foro.domain.curso.Curso;
import com.alura.foro.domain.curso.CursoRepository;
import com.alura.foro.domain.topico.dto.DatosCrearTopico;
import com.alura.foro.domain.topico.dto.DatosDetalleTopico;
import com.alura.foro.domain.topico.dto.DatosRespuestaTopico;
import com.alura.foro.domain.usuario.Usuario;
import com.alura.foro.domain.usuario.UsuarioRepository;
import com.alura.foro.domain.EntidadNoEncontradaException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoService(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    public List<DatosRespuestaTopico> obtenerTopicos(){
        return topicoRepository.findAll()
                .stream()
                .map(DatosRespuestaTopico::new)
                .toList();
    }

    public DatosRespuestaTopico crearTopico(DatosCrearTopico datos) {
        Usuario autor = usuarioRepository.findById(datos.autor_id())
                .orElseThrow(() -> new EntidadNoEncontradaException("Usuario no encontrado"));

        Curso curso = cursoRepository.findById(datos.curso_id())
                .orElseThrow(() -> new EntidadNoEncontradaException("Curso no encontrado"));

        Topico topico = new Topico(datos, autor, curso);

        Topico topicoGuardado = topicoRepository.save(topico);

        return new DatosRespuestaTopico(topicoGuardado);
    }

    public DatosDetalleTopico obtenerTopicoPorId(Long id){
        Topico topico = topicoRepository.findById(id).orElseThrow(()->new EntidadNoEncontradaException("El Id ingresado no existe"));
        return new DatosDetalleTopico(topico);
    }
}

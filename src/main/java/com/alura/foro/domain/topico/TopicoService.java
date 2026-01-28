package com.alura.foro.domain.topico;

import com.alura.foro.domain.EntidadNoEncontradaException;
import com.alura.foro.domain.curso.Curso;
import com.alura.foro.domain.curso.CursoRepository;
import com.alura.foro.domain.respuesta.Respuesta;
import com.alura.foro.domain.respuesta.dto.DatosRespuesta;
import com.alura.foro.domain.topico.dto.DatosActualizarTopico;
import com.alura.foro.domain.topico.dto.DatosCrearTopico;
import com.alura.foro.domain.topico.dto.DatosDetalleTopico;
import com.alura.foro.domain.topico.dto.DatosRespuestaTopico;
import com.alura.foro.domain.usuario.Usuario;
import com.alura.foro.domain.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<DatosRespuestaTopico> obtenerTopicos() {
        return topicoRepository.findAll()
                .stream()
                .map(DatosRespuestaTopico::new)
                .toList();
    }

    @Transactional
    public DatosRespuestaTopico crearTopico(DatosCrearTopico datos) {
        Usuario autor = usuarioRepository.findById(datos.autor_id())
                .orElseThrow(() -> new EntidadNoEncontradaException("Usuario no encontrado"));

        Curso curso = cursoRepository.findById(datos.curso_id())
                .orElseThrow(() -> new EntidadNoEncontradaException("Curso no encontrado"));

        Topico topico = new Topico(datos, autor, curso);

        Topico topicoGuardado = topicoRepository.save(topico);

        return new DatosRespuestaTopico(topicoGuardado);
    }

    public DatosDetalleTopico obtenerTopicoPorId(Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new EntidadNoEncontradaException("El Id ingresado no existe"));
        return new DatosDetalleTopico(topico);
    }

    @Transactional
    public DatosRespuestaTopico actualizarTopico(Long id, DatosActualizarTopico datosTopico) {
        Topico topico = topicoRepository.findById(id).orElseThrow(()->new EntidadNoEncontradaException("El Id ingresado no existe"));

        if(!datosTopico.validarMinimoUnDato()){
            throw new DatoMinimoException("Tiene que ingresar minimo un dato para modificar");
        }

        if (datosTopico.autor_id() != null) {
            Usuario nuevoAutor = usuarioRepository.findById(datosTopico.autor_id())
                    .orElseThrow(() -> new EntidadNoEncontradaException("Usuario no encontrado"));
            topico.setAutor(nuevoAutor);
        }

        if (datosTopico.curso_id() != null) {
            Curso nuevoCurso = cursoRepository.findById(datosTopico.curso_id())
                    .orElseThrow(() -> new EntidadNoEncontradaException("Curso no encontrado"));
            topico.setCurso(nuevoCurso);
        }

        topico.actualizarDatos(datosTopico);

        return new DatosRespuestaTopico(topico);
    }

    @Transactional
    public void eliminarTopico(Long id){
        if(!topicoRepository.existsById(id)){
            throw new EntidadNoEncontradaException("El Id ingresado no existe");
        }

        topicoRepository.deleteById(id);
    }

    public List<DatosRespuesta> respuestasPorTopico(Long id){
        List<Respuesta> respuestasTopico = topicoRepository.buscarRespuestasPorTopico(id);

        return respuestasTopico.stream()
                .map(DatosRespuesta::new)
                .toList();
    }
}

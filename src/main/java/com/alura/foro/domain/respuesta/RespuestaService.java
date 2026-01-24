package com.alura.foro.domain.respuesta;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespuestaService {
    private final RespuestaRepository respuestaRepository;

    public RespuestaService(RespuestaRepository respuestaRepository) {
        this.respuestaRepository = respuestaRepository;
    }

    public List<Respuesta> listarRespuestas(){
        return respuestaRepository.findAll();
    }

    public Respuesta crearRespuesta(Respuesta respuesta){
        return respuestaRepository.save(respuesta);
    }
}

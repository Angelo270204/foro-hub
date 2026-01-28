package com.alura.foro.domain.topico;

import com.alura.foro.domain.respuesta.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    @Query("select r from Respuesta r where r.topico.id = :id")
    List<Respuesta> buscarRespuestasPorTopico(Long id);
}
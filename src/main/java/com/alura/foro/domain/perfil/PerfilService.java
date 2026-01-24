package com.alura.foro.domain.perfil;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {
    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public List<Perfil> listarPerfiles(){
        return perfilRepository.findAll();
    }

    public Perfil crearPerfil(Perfil perfil){
        return perfilRepository.save(perfil);
    }
}

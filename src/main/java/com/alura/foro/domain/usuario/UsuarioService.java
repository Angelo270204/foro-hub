package com.alura.foro.domain.usuario;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario crearUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}

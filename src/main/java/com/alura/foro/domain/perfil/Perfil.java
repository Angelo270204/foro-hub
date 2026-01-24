package com.alura.foro.domain.perfil;

import com.alura.foro.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="perfiles")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "perfiles")
    private List<Usuario> usuarios = new ArrayList<>();
}

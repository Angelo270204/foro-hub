package com.alura.foro.domain.curso;

import com.alura.foro.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String categoria;

    @OneToMany(mappedBy = "curso",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Topico> topicos = new ArrayList<>();
}

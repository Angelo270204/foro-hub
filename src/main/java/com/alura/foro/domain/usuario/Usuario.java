package com.alura.foro.domain.usuario;

import com.alura.foro.domain.perfil.Perfil;
import com.alura.foro.domain.respuesta.Respuesta;
import com.alura.foro.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "correo_electronico", nullable = false, unique = true)
    private String correoElectronico;

    @Column(nullable = false)
    private String contrasena;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="usuario_perfiles",
            joinColumns = @JoinColumn(name="usuario_id"),
            inverseJoinColumns = @JoinColumn(name="perfil_id")
    )
    private List<Perfil> perfiles = new ArrayList<>();

    @OneToMany(mappedBy = "autor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Topico> topicos = new ArrayList<>();

    @OneToMany(mappedBy = "autor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Respuesta> respuestas = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }
}

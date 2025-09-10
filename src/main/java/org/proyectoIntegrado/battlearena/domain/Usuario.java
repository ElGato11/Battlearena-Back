package org.proyectoIntegrado.battlearena.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;
    @Column(unique = true)
    private String nombre;
    private Boolean admin;
    private String clave;
    @OneToMany
    private List<Personaje> personajes;
}

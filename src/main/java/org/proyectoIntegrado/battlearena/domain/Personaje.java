package org.proyectoIntegrado.battlearena.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idUsuario;
    private String nombre;
    private int vigor;
    private int fuerza;
    private int destreza;
    private int inteligencia;
    private int sabiduria;
    private int carisma;
}

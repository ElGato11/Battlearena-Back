package org.proyectoIntegrado.battlearena.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    private int bonoVigor;
    private int bonoFuerza;
    private int bonoDestreza;
    private int bonoInteligencia;
    private int bonoSabiduria;
    private int bonoCarisma;

}

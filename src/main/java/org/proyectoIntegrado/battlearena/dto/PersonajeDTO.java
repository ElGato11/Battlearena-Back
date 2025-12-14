package org.proyectoIntegrado.battlearena.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonajeDTO {
    private String nombre;
    private Integer fuerza;
    private Integer destreza;
    private Integer vigor;
    private Integer inteligencia;
    private Integer sabiduria;
    private Integer carisma;
    private String Clase;
}

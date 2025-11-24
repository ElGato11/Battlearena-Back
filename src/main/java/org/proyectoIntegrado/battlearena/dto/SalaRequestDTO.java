package org.proyectoIntegrado.battlearena.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalaRequestDTO {
    private String nombreSala;
    private Long personaje;
}

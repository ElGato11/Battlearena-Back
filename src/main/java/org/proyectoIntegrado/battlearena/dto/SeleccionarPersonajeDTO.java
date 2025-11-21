package org.proyectoIntegrado.battlearena.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeleccionarPersonajeDTO {
   private String nombreSala;
   private Long usuarioId;
   private Long personajeId;
}

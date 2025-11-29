package org.proyectoIntegrado.battlearena.dto;

import lombok.Data;
import org.proyectoIntegrado.battlearena.domain.Personaje;
import org.proyectoIntegrado.battlearena.domain.Sala;

@Data
public class SalaMessageDTO {
    private int id;
    private String nombre;
    private String anfitrion;
    private String contrincante;
    private Personaje pAnfitrion;
    private Personaje pContrincante;
    private float hpA;
    private float hpC;

    public SalaMessageDTO(Sala sala, String contrincante, String anfitrion) {
        this.id = sala.getId();
        this.nombre = sala.getNombre();
        this.anfitrion = anfitrion;
        this.contrincante = contrincante;
        this.pAnfitrion = sala.getPAnfitrion();
        this.pContrincante = sala.getPContrincante();
    }
    public SalaMessageDTO(){
    }
}


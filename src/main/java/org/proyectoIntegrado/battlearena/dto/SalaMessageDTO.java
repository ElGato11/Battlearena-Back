package org.proyectoIntegrado.battlearena.dto;

import lombok.Data;
import org.proyectoIntegrado.battlearena.domain.Personaje;
import org.proyectoIntegrado.battlearena.domain.Sala;

@Data
public class SalaMessageDTO {
    private long id;
    private String nombre;
    private String anfitrion;
    private String contrincante;
    private Personaje pAnfitrion;
    private Personaje pContrincante;
    private float hpA;
    private float hpC;
    private boolean combateIniciado;
    private boolean turnoAnfitrion;

    public SalaMessageDTO(Sala sala, String contrincante, String anfitrion) {
        this.id = sala.getId();
        this.nombre = sala.getNombre();
        this.anfitrion = anfitrion;
        this.contrincante = contrincante;
        this.pAnfitrion = sala.getPAnfitrion();
        this.pContrincante = sala.getPContrincante();
        this.hpA = sala.getHpA();
        this.hpC = sala.getHpC();
        this.combateIniciado = sala.isCombateIniciado();
        this.turnoAnfitrion = sala.isTurnoAnfitrion();

    }
    public SalaMessageDTO(){
    }
}


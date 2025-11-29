package org.proyectoIntegrado.battlearena.domain;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;


@Data
public class Sala {
    private int id;
    private String nombre;
    private Personaje pAnfitrion;
    private Personaje pContrincante;
    private float hpA;
    private float hpC;

    private static final AtomicInteger contadorId = new AtomicInteger(0); //garantiza que cada id sea unico

    public Sala( String nombre, Personaje anfitrion) {
        this.id = contadorId.incrementAndGet();
        this.nombre = nombre;
        this.pAnfitrion = anfitrion;
    }

    public Sala(Sala sala){
        this.id = sala.id;
        this.nombre = sala.nombre;
        this.pAnfitrion = sala.pAnfitrion;
        this.pContrincante = sala.pContrincante;
        this.hpA = sala.hpA;
        this.hpC = sala.hpC;
    }

    public Sala() {
    }
}

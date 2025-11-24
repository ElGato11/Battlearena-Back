package org.proyectoIntegrado.battlearena.domain;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;


@Data
public class Sala {
    private int id;
    private String nombre;
    private Personaje pAnfitrion;
    private Personaje pContrincante;

    private static final AtomicInteger contadorId = new AtomicInteger(0); //garantiza que cada id sea unico

    public Sala( String nombre, Personaje anfitrion) {
        this.id = contadorId.incrementAndGet();
        this.nombre = nombre;
        this.pAnfitrion = anfitrion;
    }


}

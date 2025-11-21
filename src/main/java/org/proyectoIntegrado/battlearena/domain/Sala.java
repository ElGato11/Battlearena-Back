package org.proyectoIntegrado.battlearena.domain;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;


@Data
public class Sala {
    private int id;
    private String nombre;
    private Long anfitrion;
    private Long contrincante;
    private Long pAnfitrion;
    private Long pContrincante;

    private static final AtomicInteger contadorId = new AtomicInteger(0); //garantiza que cada id sea unico

    public Sala( String nombre, Long anfitrion) {
        this.id = contadorId.incrementAndGet();
        this.nombre = nombre;
        this.anfitrion = anfitrion;
    }


}

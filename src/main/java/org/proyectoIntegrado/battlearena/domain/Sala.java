package org.proyectoIntegrado.battlearena.domain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;


@Data
@NoArgsConstructor
public class Sala {
    private long id;
    private String nombre;
    private Personaje pAnfitrion;
    private Personaje pContrincante;
    private float hpA;
    private float hpC;
    private boolean combateIniciado;
    private boolean turnoAnfitrion;

    private static final AtomicInteger contadorId = new AtomicInteger(0); //garantiza que cada id sea unico

    public Sala( String nombre, Personaje anfitrion) {
        this.id = contadorId.incrementAndGet();
        this.nombre = nombre;
        this.pAnfitrion = anfitrion;
        combateIniciado = false;
    }

    public void iniciarCombate(){
        this.combateIniciado = true;
        this.hpA = this.pAnfitrion.getMaxHp();
        this.hpC = this.pContrincante.getMaxHp();
        this.turnoAnfitrion = this.pAnfitrion.getDestreza() > this.pContrincante.getDestreza();
    }

    public void atacar(long id){
        if(this.pAnfitrion.getId() == id){
            hpC -= this.pAnfitrion.getFuerza();
        }else{
            hpA -= this.pContrincante.getFuerza();
        }
        this.turnoAnfitrion = !this.turnoAnfitrion;

    }
}

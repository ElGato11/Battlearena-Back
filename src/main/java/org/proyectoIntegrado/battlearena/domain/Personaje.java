package org.proyectoIntegrado.battlearena.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_clase")
    private Clase clase;

    private String nombre;
    private int vigor;
    private int fuerza;
    private int destreza;
    private int inteligencia;
    private int sabiduria;
    private int carisma;

    public float getMaxHp(){
        return (this.getVigorTotal() +  this.clase.getBonoVigor()) * 2;
    }
    public int getVigorTotal() { return vigor + clase.getBonoVigor(); }
    public int getFuerzaTotal() { return fuerza + clase.getBonoFuerza(); }
    public int getDestrezaTotal() { return destreza + clase.getBonoDestreza(); }
    public int getInteligenciaTotal() { return inteligencia + clase.getBonoInteligencia(); }
    public int getSabiduriaTotal() { return sabiduria + clase.getBonoSabiduria(); }
    public int getCarismaTotal() { return carisma + clase.getBonoCarisma(); }

}

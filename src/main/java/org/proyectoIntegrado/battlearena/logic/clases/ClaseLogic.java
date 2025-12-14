package org.proyectoIntegrado.battlearena.logic.clases;

import org.proyectoIntegrado.battlearena.domain.Personaje;

public interface ClaseLogic {
    String[] getAcciones();
    int ejecutarAccion(String accion, Personaje atacante, Personaje defensor);
}
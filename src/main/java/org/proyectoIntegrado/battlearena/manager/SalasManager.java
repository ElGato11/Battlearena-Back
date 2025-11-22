package org.proyectoIntegrado.battlearena.manager;

import org.proyectoIntegrado.battlearena.domain.Sala;
import org.proyectoIntegrado.battlearena.exception.SalaNameExistException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/salas")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class SalasManager {

    private final Map<String, Sala> salas = new HashMap<>();
    private final SimpMessagingTemplate messagingTemplate;

    public SalasManager(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    public Sala crearSala(String nombre, Long anfitrion) {
        Sala sala = new Sala(nombre, anfitrion);
        if (existe(nombre)) {
            throw new SalaNameExistException(nombre);
        }
        salas.put(sala.getNombre(), sala);
        messagingTemplate.convertAndSend("/topic/sala/" + sala.getNombre(), sala);
        return sala;
    }

    private Boolean existe(String nombre){
        return salas.containsKey(nombre);
    }

    public Optional<Sala> getSala(String nombre) {
        return Optional.ofNullable(salas.get(nombre));
    }

    public Collection<Sala> getTodas() {
        return salas.values();
    }

    public void eliminarSala(String nombre) {
        salas.remove(nombre);
    }

    public void unirJugador(String nombre, Long contrincante) {
        Sala sala = salas.get(nombre);
        if (sala != null && !sala.getAnfitrion().equals(contrincante)) {
            sala.setContrincante(contrincante);
            messagingTemplate.convertAndSend("/topic/sala/" + sala.getNombre(), sala);
        }
    }

    public boolean seleccionarPersonaje(String nombreSala, Long usuarioId, Long idPersonaje) {
        Sala sala = salas.get(nombreSala);
        boolean ret = false;
        if (sala == null) return false; //revisalo luego
        if (usuarioId.equals(sala.getAnfitrion())) {
            sala.setPAnfitrion(idPersonaje);
            ret = true;
        } else if (usuarioId.equals(sala.getContrincante())) {
            sala.setPContrincante(idPersonaje);
            ret = true;
        }
        messagingTemplate.convertAndSend("/topic/sala/" + sala.getNombre(), sala);
        return ret;
    }


}

package org.proyectoIntegrado.battlearena.manager;

import org.proyectoIntegrado.battlearena.domain.Personaje;
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


    public Sala crearSala(String nombre, Personaje pAnfitrion) {
        Sala sala = new Sala(nombre, pAnfitrion);
        if (existe(nombre)) {
            throw new SalaNameExistException(nombre);
        }
        salas.put(sala.getNombre(), sala);
        messagingTemplate.convertAndSend("/topic/lista-salas", this.getTodas());
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
        messagingTemplate.convertAndSend("/topic/lista-salas", this.getTodas());
    }

    public boolean unirJugador(String nombre, Personaje pContrincante) {
        boolean ret = false;
        Sala sala = salas.get(nombre);
        if (sala != null && !sala.getPAnfitrion().getUsuario().getIdUsuario().equals(pContrincante.getUsuario().getIdUsuario())) {
            sala.setPContrincante(pContrincante);
            messagingTemplate.convertAndSend("/topic/sala/" + sala.getNombre(), sala);
            ret = true;
        }
        return ret;
    }


}

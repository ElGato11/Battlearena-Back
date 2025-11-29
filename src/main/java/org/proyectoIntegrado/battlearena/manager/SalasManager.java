package org.proyectoIntegrado.battlearena.manager;

import org.proyectoIntegrado.battlearena.domain.Personaje;
import org.proyectoIntegrado.battlearena.domain.Sala;
import org.proyectoIntegrado.battlearena.dto.SalaMessageDTO;
import org.proyectoIntegrado.battlearena.exception.SalaNameExistException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import javax.validation.constraints.Null;
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
        wsTodas();
        return sala;
    }

    private Boolean existe(String nombre){
        return salas.containsKey(nombre);
    }

    public Sala getSala(String nombre) {
        return salas.get(nombre);
    }
    public SalaMessageDTO getSalaRequest(String nombre){
        return salaRequest(this.getSala(nombre));
    }

    public Collection<Sala> getTodas() {
        return salas.values();

    }

    public void eliminarSala(String nombre) {
        salas.remove(nombre);
        wsTodas();
        wsSala(null, nombre);
    }

    public boolean unirJugador(String nombre, Personaje pContrincante) {
        boolean ret = false;
        Sala sala = salas.get(nombre);
        if (sala != null && !sala.getPAnfitrion().getUsuario().getIdUsuario().equals(pContrincante.getUsuario().getIdUsuario())) {
            sala.setPContrincante(pContrincante);
            wsSala(sala, sala.getNombre());
            ret = true;
        }
        return ret;
    }

    public void eliminarContrincante(String nombre){
        Sala sala = salas.get(nombre);
        sala.setPContrincante(null);
        wsSala(sala, sala.getNombre());
    }

    private void wsSala(Sala sala, String nombre){
        SalaMessageDTO salaMessageDTO = salaRequest(sala);
        messagingTemplate.convertAndSend("/topic/sala/" + nombre, salaMessageDTO);
    }

    private void wsTodas(){
        messagingTemplate.convertAndSend("/topic/lista-salas", this.getTodas());
    }

    private SalaMessageDTO salaRequest(Sala sala){
        if(sala == null)return new SalaMessageDTO();
        String nombreContrincante = null;
        if(sala.getPContrincante() != null && sala.getPContrincante().getUsuario() != null) nombreContrincante = sala.getPContrincante().getUsuario().getNombre();
        return new SalaMessageDTO(sala, nombreContrincante, sala.getPAnfitrion().getUsuario().getNombre());
    }


}

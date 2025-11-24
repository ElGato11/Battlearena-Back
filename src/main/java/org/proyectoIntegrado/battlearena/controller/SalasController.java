package org.proyectoIntegrado.battlearena.controller;
import org.proyectoIntegrado.battlearena.domain.Personaje;
import org.proyectoIntegrado.battlearena.domain.Sala;
import org.proyectoIntegrado.battlearena.dto.SalaMessageDTO;
import org.proyectoIntegrado.battlearena.dto.SalaRequestDTO;
import org.proyectoIntegrado.battlearena.manager.SalasManager;
import org.proyectoIntegrado.battlearena.service.PersonajeService;
import org.proyectoIntegrado.battlearena.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/salas")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class SalasController {
    @Autowired
    private final SalasManager salasManager;
    @Autowired
    private final UsuarioService usuarioService;
    @Autowired
    private final PersonajeService personajeService;

    public SalasController(SalasManager salasManager, UsuarioService usuarioService, PersonajeService personajeService) {this.salasManager = salasManager;
        this.usuarioService = usuarioService;
        this.personajeService = personajeService;
    }

    @GetMapping("/lista-salas")
    public ResponseEntity<Collection<Sala>> listarSalas(){
        return ResponseEntity.ok(salasManager.getTodas());
    }

    @PostMapping("/crear")
    public ResponseEntity<Sala> crearSala(@RequestBody SalaRequestDTO body) {
        String nombre =body.getNombreSala();
        Long personaje =body.getPersonaje();
        Personaje anfitrion = personajeService.findPersonaje(personaje);
        Sala salaCreada = salasManager.crearSala(nombre, anfitrion);
        return ResponseEntity.ok(salaCreada);
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<SalaMessageDTO> getSala(@PathVariable String nombre) {
        SalaMessageDTO sala = salasManager.getSalaRequest(nombre);
        return ResponseEntity.ok(sala);
    }

    @GetMapping("/existe/{nombre}")
    public ResponseEntity<Boolean> existeSala(@PathVariable String nombre) {
        boolean existe = salasManager.getSala(nombre) != null;
        return ResponseEntity.ok(existe);
    }

    @DeleteMapping("/borrar/{nombre}")
    public ResponseEntity<Void> borrarSala(@PathVariable String nombre) {
        salasManager.eliminarSala(nombre);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/unirse")
    public ResponseEntity<Boolean> unirseSala(@RequestBody SalaRequestDTO body) {
        Personaje personaje = personajeService.findPersonaje(body.getPersonaje());
        return ResponseEntity.ok(salasManager.unirJugador(body.getNombreSala(),personaje));
    }

}

package org.proyectoIntegrado.battlearena.controller;

import org.proyectoIntegrado.battlearena.domain.Sala;
import org.proyectoIntegrado.battlearena.dto.SeleccionarPersonajeDTO;
import org.proyectoIntegrado.battlearena.manager.SalasManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/salas")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class SalasController {
    @Autowired
    private final SalasManager salasManager;

    public SalasController(SalasManager salasManager) {this.salasManager = salasManager;}

    @GetMapping("/lista-salas")
    public ResponseEntity<Collection<Sala>> listarSalas(){
        return ResponseEntity.ok(salasManager.getTodas());
    }

    @PostMapping("/crear")
    public ResponseEntity<Sala> crearSala(@RequestBody Map<String, Object> body) {
        String nombre = (String) body.get("nombre");
        Long anfitrion = Long.valueOf(body.get("id").toString());
        Sala salaCreada = salasManager.crearSala(nombre, anfitrion);
        return ResponseEntity.ok(salaCreada);
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Sala> getSala(@PathVariable String nombre) {
        Sala sala = salasManager.getSala(nombre).orElse(null);
        return ResponseEntity.ok(sala);
    }

    @PostMapping("/seleccionar-personaje")
    //devuelvo un 200 sin contenido asi que ?
    public ResponseEntity<Void> seleccionarPersonaje(@RequestBody SeleccionarPersonajeDTO req) {

        salasManager.seleccionarPersonaje(
                req.getNombreSala(),
                req.getUsuarioId(),
                req.getPersonajeId()
        );

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/existe/{nombre}")
    public ResponseEntity<Boolean> existeSala(@PathVariable String nombre) {
        boolean existe = salasManager.getSala(nombre).isPresent();
        return ResponseEntity.ok(existe);
    }
}

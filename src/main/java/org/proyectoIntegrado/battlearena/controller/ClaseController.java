package org.proyectoIntegrado.battlearena.controller;

import lombok.RequiredArgsConstructor;
import org.proyectoIntegrado.battlearena.domain.Clase;
import org.proyectoIntegrado.battlearena.domain.Usuario;
import org.proyectoIntegrado.battlearena.service.ClaseService;
import org.proyectoIntegrado.battlearena.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clase")
@CrossOrigin(origins = {"http://localhost:4200/"})
@RequiredArgsConstructor
public class ClaseController {

    private final ClaseService claseService;

    @GetMapping("/")
    public ResponseEntity<List<Clase>> listarClase(){
        return ResponseEntity.ok(claseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clase> getById(@PathVariable Long id) {
        Clase clase = claseService.findById(id);
        if (clase == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(clase);
    }

    @PostMapping
    public Clase create(@RequestBody Clase clase) {
        return claseService.save(clase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clase> update(@PathVariable Long id, @RequestBody Clase updated) {
        Clase existing = claseService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        existing.setNombre(updated.getNombre());
        existing.setComando(updated.getComando());
        existing.setBonoVigor(updated.getBonoVigor());
        existing.setBonoFuerza(updated.getBonoFuerza());
        existing.setBonoDestreza(updated.getBonoDestreza());
        existing.setBonoInteligencia(updated.getBonoInteligencia());
        existing.setBonoSabiduria(updated.getBonoSabiduria());
        existing.setBonoCarisma(updated.getBonoCarisma());

        return ResponseEntity.ok(claseService.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Clase existing = claseService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        claseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

package org.proyectoIntegrado.battlearena.controller;




import org.proyectoIntegrado.battlearena.domain.Personaje;
import org.proyectoIntegrado.battlearena.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personaje")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class PersonajeController {

    @Autowired
    private final PersonajeService personajeService;

    public PersonajeController(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Personaje>> listarPersonajes(){
        return ResponseEntity.ok(personajeService.getAll());
    }

    @PostMapping("/crear")
    public ResponseEntity<Personaje> crearPersonaje(@RequestBody Personaje personaje){
        return ResponseEntity.ok(personajeService.save(personaje));
    }

    @PostMapping("/borrar/{id}")
    public ResponseEntity<Void> borrarPersonaje(@PathVariable Long id){
        personajeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/editar")
    public ResponseEntity<Personaje> editarPersonaje(@RequestBody Personaje personaje){
        return ResponseEntity.ok(personajeService.update(personaje));
    }
}

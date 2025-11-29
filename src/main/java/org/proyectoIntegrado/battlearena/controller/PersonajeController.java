package org.proyectoIntegrado.battlearena.controller;




import org.proyectoIntegrado.battlearena.domain.Personaje;
import org.proyectoIntegrado.battlearena.dto.PersonajeDTO;
import org.proyectoIntegrado.battlearena.service.PersonajeService;
import org.proyectoIntegrado.battlearena.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personaje")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class PersonajeController {


    private final PersonajeService personajeService;
    private final UsuarioService usuarioService;

    public PersonajeController(PersonajeService personajeService, UsuarioService usuarioService) {
        this.personajeService = personajeService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Personaje>> listarPersonajes(){
        return ResponseEntity.ok(personajeService.getAll());
    }

    @PostMapping("/crear/{idUsuario}")
    public ResponseEntity<Personaje> crearPersonaje( @PathVariable Long idUsuario,@RequestBody PersonajeDTO personaje){
        return ResponseEntity.ok(usuarioService.crearPersonaje(idUsuario,personaje));
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> borrarPersonaje(@PathVariable Long id){
        System.out.println("aqui llega" + id);
        personajeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/editar")
    public ResponseEntity<Personaje> editarPersonaje(@RequestBody Personaje personaje){
        return ResponseEntity.ok(personajeService.update(personaje));
    }
}

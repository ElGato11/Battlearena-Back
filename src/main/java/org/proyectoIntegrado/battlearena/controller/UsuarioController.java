package org.proyectoIntegrado.battlearena.controller;

import org.proyectoIntegrado.battlearena.domain.Personaje;
import org.proyectoIntegrado.battlearena.domain.Usuario;
import org.proyectoIntegrado.battlearena.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.save(usuario));
    }

    @PostMapping("/borrar/{id}")
    public ResponseEntity<Void> borrarUsuario(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/personajes/{id}")
    public ResponseEntity<List<Personaje>> personajesUsuario(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.getPersonajes(id));
    }

    @PostMapping("/editar")
    public ResponseEntity<Usuario> editarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.save(usuario));
    }
}

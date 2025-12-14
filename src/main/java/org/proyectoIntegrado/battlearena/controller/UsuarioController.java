package org.proyectoIntegrado.battlearena.controller;

import org.proyectoIntegrado.battlearena.domain.Personaje;
import org.proyectoIntegrado.battlearena.domain.Usuario;
import org.proyectoIntegrado.battlearena.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @GetMapping("/crear-admin")
    public ResponseEntity<?> crearAdmin() {
        if(usuarioService.existeByNombre("admin")){
            return ResponseEntity.status(409).body("El nombre ya está en uso");
        }
        return ResponseEntity.ok(usuarioService.save(new Usuario(Long.parseLong("1"), "admin", true, "1234", "", new ArrayList<>())));
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Usuario usuario) {

        usuario.setAdmin(false);

        if (usuario.getNombre().equalsIgnoreCase("admin")) {
            return ResponseEntity.status(403).body("No puedes usar el nombre 'admin'");
        }

        if (usuarioService.existeByNombre(usuario.getNombre())) {
            return ResponseEntity.status(409).body("El nombre ya está en uso");
        }

        return ResponseEntity.ok(usuarioService.save(usuario));
    }



    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> borrarUsuario(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/personajes/{id}")
    public ResponseEntity<List<Personaje>> personajesUsuario(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.getPersonajes(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.findByIdUsuario(id));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Usuario> editarUsuario(
            @PathVariable long id,
            @RequestBody Map<String, Object> cambios) {

        return ResponseEntity.ok(usuarioService.editar(id, cambios));
    }


    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Map<String, String> body) {
        String nombre = body.get("nombre");
        String clave = body.get("clave");
        return ResponseEntity.ok(usuarioService.login(nombre, clave));
    }

    @PutMapping("/cambiar-clave/{id}")
    public ResponseEntity<Usuario> cambiarClave(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(usuarioService.cambiarClave(id, body.get("clave")));
    }

    @PostMapping("/imagen/{id}")
    public ResponseEntity<String> subirFoto(
            @PathVariable Long id,
            @RequestParam("foto") MultipartFile file) {

        try {
            String url = usuarioService.guardarFoto(id, file);
            return ResponseEntity.ok(url);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al subir la foto");
        }
    }


}

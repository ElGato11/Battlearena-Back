package org.proyectoIntegrado.battlearena.service;


import jakarta.persistence.EntityNotFoundException;
import org.proyectoIntegrado.battlearena.domain.Clase;
import org.proyectoIntegrado.battlearena.domain.Personaje;
import org.proyectoIntegrado.battlearena.domain.Usuario;
import org.proyectoIntegrado.battlearena.dto.PersonajeDTO;
import org.proyectoIntegrado.battlearena.repository.ClaseRepository;
import org.proyectoIntegrado.battlearena.repository.PersonajeRepository;
import org.proyectoIntegrado.battlearena.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class UsuarioService {

    private final ClaseRepository claseRepository;
    private final UsuarioRepository usuarioRepository;
    private final PersonajeRepository personajeRepository;

    public UsuarioService(ClaseRepository claseRepository, UsuarioRepository usuarioRepository, PersonajeRepository personajeRepository) {
        this.claseRepository = claseRepository;
        this.usuarioRepository = usuarioRepository;
        this.personajeRepository = personajeRepository;
    }

    public List<Usuario> getAll() {
       return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        if (usuarioRepository.findByNombre(usuario.getNombre()) != null) { //solucion alternativa .getNombre()
            throw new RuntimeException("Ya existe un usuario con ese nombre.");
        }
        Usuario nuevoUsuario = Usuario.builder()
                .nombre(usuario.getNombre())
                .clave(usuario.getClave())
                .admin(usuario.getAdmin())
                .personajes(Collections.emptyList()).build();

        return usuarioRepository.save(nuevoUsuario);
    }

    public Personaje crearPersonaje(Long idUsuario, PersonajeDTO dto) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " +  idUsuario));
        Clase clase = claseRepository.findByNombre(dto.getClase());
        Personaje nuevoPersonaje = Personaje.builder()
                .usuario(usuario)
                .nombre(dto.getNombre())
                .fuerza(dto.getFuerza())
                .destreza(dto.getDestreza())
                .vigor(dto.getVigor())
                .inteligencia(dto.getInteligencia())
                .sabiduria(dto.getSabiduria())
                .carisma(dto.getCarisma())
                .clase(clase)
                .build();
        System.out.println(dto.toString());
        return personajeRepository.save(nuevoPersonaje);
    }

    public void delete(Long id) {
            usuarioRepository.deleteById(id);
    }

    public List<Personaje> getPersonajes(Long idUsuario) {
        return personajeRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public Usuario login(String nombre, String clave) {
        Usuario u = usuarioRepository.findByNombre(nombre);
        if (u == null) throw new RuntimeException("Usuario no encontrado");
        if (!u.getClave().equals(clave)) throw new RuntimeException("Contraseña incorrecta");
        return u;
    }

    public Usuario cambiarClave(Long id, String clave){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + id));
        usuario.setClave(clave);
        return usuarioRepository.save(usuario);
    }

    //deberia concentrar los path en variable?
    public String guardarFoto(Long id, MultipartFile file) throws IOException {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        if (file.isEmpty()) {
            throw new IllegalArgumentException("No se cargó ninguna imagen");
        }

        // Validar tipo ¿MIME?
        String contentType = file.getContentType();
        if (contentType == null ||
                !(contentType.equals("image/jpeg") || contentType.equals("image/png"))) {
            throw new IllegalArgumentException("Solo se permiten imágenes JPG o PNG");
        }

        // Validar tamaño (5MB máximo)
        long maxSize = 5 * 1024 * 1024;//me da pereza calcular
        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException("La imagen no puede superar los 5MB");
        }

        // Asegurar directorio
        Path uploadDir = Paths.get("uploads/imagenes-perfil-usuario/");
        Files.createDirectories(uploadDir);

        // Borrar foto anterior si existiera
        if (usuario.getFoto() != null) {
            Path oldPath = Paths.get("uploads/imagenes-perfil-usuario/",
                    Paths.get(usuario.getFoto()).getFileName().toString());
            Files.deleteIfExists(oldPath);
        }

        // Las extensiones que me apetecen
        String extension = contentType.equals("image/png") ? ".png" : ".jpg";

        // Nombre único
        String fileName = "user_" + id + "_" + UUID.randomUUID() + extension;

        Path filePath = uploadDir.resolve(fileName);
        Files.write(filePath, file.getBytes());

        // URL expuesta, acuerdate que esto va con el properties
        String url = "/uploads/imagenes-perfil-usuario/" + fileName;

        usuario.setFoto(url);
        usuarioRepository.save(usuario);

        return url;
    }

    public Boolean existeByNombre(String nombre){
        return usuarioRepository.existsByNombre(nombre);
    }

    public Usuario editar(long id, Map<String, Object> cambios) {

        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        cambios.forEach((campo, valor) -> {
            switch (campo) {
                case "nombre" -> existente.setNombre((String) valor);
                case "admin" -> existente.setAdmin((Boolean) valor);
            }
        });

        return usuarioRepository.save(existente);
    }


    public Usuario findByIdUsuario(Long id) {
        return this.usuarioRepository.findById(id).orElseThrow(()->new RuntimeException("no se encontró el usuario"));
    }
}

package org.proyectoIntegrado.battlearena.service;



import org.proyectoIntegrado.battlearena.domain.Personaje;
import org.proyectoIntegrado.battlearena.domain.Usuario;
import org.proyectoIntegrado.battlearena.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.Collections;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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

    public void delete(Long id) {
        Usuario usuarioBorrado = usuarioRepository.findById(id).orElse(null);
        if(usuarioBorrado != null){
            usuarioRepository.delete(usuarioBorrado);
        }
    }

    public List<Personaje> getPersonajes(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario != null){
            return usuario.getPersonajes();
        }
        return Collections.emptyList();
    }

    public Usuario login(String nombre, String clave) {
        System.out.println(nombre + clave);
        Usuario u = usuarioRepository.findByNombre(nombre);
        if (u == null) throw new RuntimeException("Usuario no encontrado");
        if (!u.getClave().equals(clave)) throw new RuntimeException("Contraseña incorrecta");
        return u;
    }

    public boolean findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre) != null; //comprobar, igual que arriba
    }
}

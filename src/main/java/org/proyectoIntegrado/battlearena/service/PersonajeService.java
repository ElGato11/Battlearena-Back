package org.proyectoIntegrado.battlearena.service;


import org.proyectoIntegrado.battlearena.domain.Personaje;
import org.proyectoIntegrado.battlearena.domain.Usuario;
import org.proyectoIntegrado.battlearena.repository.PersonajeRepository;
import org.proyectoIntegrado.battlearena.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersonajeService {
    @Autowired
    private final PersonajeRepository personajeRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;

    public PersonajeService(PersonajeRepository personajeRepository, UsuarioRepository usuarioRepository) {
        this.personajeRepository = personajeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Personaje> getAll() {
        return personajeRepository.findAll();
    }


    public void delete(Long id){
        personajeRepository.deleteById(id);
    }

    public Personaje update(Personaje personaje) {
        personajeRepository.findById(personaje.getId()).orElseThrow(() -> new RuntimeException("Personaje no encontrado, id: "+personaje.getId()));
        return personajeRepository.save(personaje);
    }

    public Personaje findPersonaje(Long id){
        return personajeRepository.findById(id).orElseThrow(() -> new RuntimeException("Personaje no encontrado, id: "+ id));
    }

    public Personaje editar(long id, Map<String, Object> cambios) {

        Personaje existente = personajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personaje no encontrado"));

        cambios.forEach((campo, valor) -> {
            switch (campo) {
                case "nombre" -> existente.setNombre((String) valor);
                case "fuerza" -> existente.setFuerza((Integer) valor);
                case "destreza" -> existente.setDestreza((Integer) valor);
                case "vigor" -> existente.setVigor((Integer) valor);
                case "inteligencia" -> existente.setInteligencia((Integer) valor);
                case "sabiduria" -> existente.setSabiduria((Integer) valor);
                case "carisma" -> existente.setCarisma((Integer) valor);
            }
        });


        return personajeRepository.save(existente);
    }
}

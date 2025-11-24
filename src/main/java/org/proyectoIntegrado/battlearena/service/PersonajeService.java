package org.proyectoIntegrado.battlearena.service;


import org.proyectoIntegrado.battlearena.domain.Personaje;
import org.proyectoIntegrado.battlearena.repository.PersonajeRepository;
import org.proyectoIntegrado.battlearena.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Personaje personajeBorrado = personajeRepository.findById(id).orElse(null);
        if(personajeBorrado != null){
            personajeRepository.delete(personajeBorrado);
        }
    }

    public Personaje update(Personaje personaje) {
        personajeRepository.findById(personaje.getId()).orElseThrow(() -> new RuntimeException("Personaje no encontrado, id: "+personaje.getId()));
        return personajeRepository.save(personaje);
    }

    public Personaje findPersonaje(Long id){
        return personajeRepository.findById(id).orElseThrow(() -> new RuntimeException("Personaje no encontrado, id: "+ id));
    }
}

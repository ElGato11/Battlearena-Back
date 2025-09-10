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

    public Personaje save(Personaje personaje) {
        usuarioRepository.findById(personaje.getIdUsuario()).orElseThrow(() -> new RuntimeException("el usuario no existe id: " + personaje.getIdUsuario()));
        Personaje nuevoPersonaje = Personaje.builder()
                .idUsuario(personaje.getIdUsuario())
                .nombre(personaje.getNombre())
                .vigor(personaje.getVigor())
                .fuerza(personaje.getFuerza())
                .destreza(personaje.getDestreza())
                .inteligencia(personaje.getInteligencia())
                .carisma(personaje.getCarisma())
                .sabiduria(personaje.getSabiduria())
                .build();

        return personajeRepository.save(nuevoPersonaje);
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
}

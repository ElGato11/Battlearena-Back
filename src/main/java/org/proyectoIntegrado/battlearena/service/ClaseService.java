package org.proyectoIntegrado.battlearena.service;

import lombok.RequiredArgsConstructor;
import org.proyectoIntegrado.battlearena.domain.Clase;
import org.proyectoIntegrado.battlearena.repository.ClaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaseService {
    private final ClaseRepository claseRepository;

    public List<Clase> findAll() {
        return claseRepository.findAll();
    }

    public Clase findById(Long id) {
        return claseRepository.findById(id).orElse(null);
    }

    public Clase save(Clase clase) {
        return claseRepository.save(clase);
    }

    public void delete(Long id) {
        claseRepository.deleteById(id);
    }
}

package org.proyectoIntegrado.battlearena.repository;


import org.proyectoIntegrado.battlearena.domain.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
}

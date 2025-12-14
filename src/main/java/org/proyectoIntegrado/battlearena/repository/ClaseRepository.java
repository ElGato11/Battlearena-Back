package org.proyectoIntegrado.battlearena.repository;

import org.proyectoIntegrado.battlearena.domain.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long>{

    Clase findByNombre(String nombre);
}

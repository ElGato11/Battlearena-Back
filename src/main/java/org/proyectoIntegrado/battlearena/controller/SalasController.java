package org.proyectoIntegrado.battlearena.controller;

import org.proyectoIntegrado.battlearena.service.PersonajeService;
import org.proyectoIntegrado.battlearena.service.SalasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personaje")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class SalasController {

    @Autowired
    private final SalasService salasService;

    public SalasController(SalasService salasService) {
        this.salasService = salasService;
    }
}

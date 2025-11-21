package org.proyectoIntegrado.battlearena.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class SalaNameExistException extends RuntimeException{
    public SalaNameExistException(String name) {
        super("This name is on use: " + name);
    }
}

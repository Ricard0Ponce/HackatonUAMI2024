package uam.mx.sintra.articulos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uam.mx.sintra.articulos.models.Error404;

import java.util.Date;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Error404> runtimeExceptionHandler(RuntimeException ex) {
        Error404 error = new Error404().builder()
                .error("404")
                .message("No se ha encontrado el articulo.")
                .timestamp(String.valueOf(new Date()))
                .status(404)
                .build();
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }





}

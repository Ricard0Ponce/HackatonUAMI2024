package uam.mx.sintra.articulos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uam.mx.sintra.articulos.exceptions.ListEmptyException;
import uam.mx.sintra.articulos.exceptions.RequestException;
import uam.mx.sintra.articulos.models.Error400;
import uam.mx.sintra.articulos.models.Error404;
import uam.mx.sintra.articulos.models.ErrorDTO;

import java.util.Date;

@RestControllerAdvice // Esta anotacion nos permite manejar excepciones de manera global
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class) // Le pasamos la clase a la que vamos a generar la excepcion
    public ResponseEntity<Error404> runtimeExceptionHandler(RuntimeException ex) {
        Error404 error = new Error404().builder()
                .status(404)
                .error("Error en la solicitud.")
                .timestamp(String.valueOf(new Date()))
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value= ListEmptyException.class)
    public ResponseEntity<Error404> listExceptionHandler(RuntimeException ex){
        Error404 error = new Error404().builder()
                .status(404)
                .error("Elementos no encontrados")
                .timestamp(String.valueOf(new Date()))
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value= RequestException.class)
    public ResponseEntity<ErrorDTO> requestExceptionHandler(RuntimeException ex) {
        ErrorDTO error = new ErrorDTO().builder()
                .timestamp(new Date())
                .status(400)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}

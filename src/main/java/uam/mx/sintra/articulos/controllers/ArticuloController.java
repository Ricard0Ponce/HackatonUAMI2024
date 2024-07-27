package uam.mx.sintra.articulos.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import uam.mx.sintra.articulos.api.ApiArticulos;
import uam.mx.sintra.articulos.models.*;
import uam.mx.sintra.articulos.services.ArticuloService;

import java.util.Date;
import java.util.List;

@Validated
@RestController
public class ArticuloController implements ApiArticulos {

    @Autowired
    private ArticuloService articuloService;

    @Override
    public ResponseEntity<?> deleteArticuloById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(articuloService.deleteArticulo(id));
    }

    @Override
    public ResponseEntity<List<ArticuloResponse>> getAllArticulos() {
        return ResponseEntity.ok(articuloService.getAllArticulos());
    }

    @Override
    public ResponseEntity<?> getAllArticulosByCategoria(String categoria) {
        // Agregar el try and catch y con ello el else
        if(articuloService.getAllArticulos().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error404(String.valueOf(new Date()), 404, "No hay articulos en la base de datos.", "ArticuloNotFoundException"));
        }
        return null;
    }

    @Override
    public ResponseEntity<ArticuloResponse> getArticuloById(Long id) {
        // Solo devolvemos esto, si sucede una excepcion esta sucede en el controlador y devolvera el resultado correspondiente
        return ResponseEntity.status(HttpStatus.OK).body(articuloService.getArticuloById(id).get());
    }

    // Permite postear un articulo
    @Override
    public ResponseEntity<ArticuloResponse> postArticulo(ArticuloRequest body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articuloService.createArticulo(body).get());
    }

    // Permite actualizar un articulo por su id
    @Override
    public ResponseEntity<ArticuloResponse> putArticuloById(Long id, ArticuloRequest body) {
        return ResponseEntity.status(HttpStatus.OK).body(articuloService.updateArticulo(id, body).get());
    }
}

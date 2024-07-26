package uam.mx.sintra.articulos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import uam.mx.sintra.articulos.api.ApiArticulos;
import uam.mx.sintra.articulos.exceptions.ArticuloNotFoundException;
import uam.mx.sintra.articulos.models.Articulo;
import uam.mx.sintra.articulos.models.ArticuloRequest;
import uam.mx.sintra.articulos.models.ArticuloResponse;
import uam.mx.sintra.articulos.services.ArticuloService;
import java.util.List;

@Validated
@RestController
public class ArticuloController implements ApiArticulos {

    @Autowired
    private ArticuloService articuloService;

    @Override
    public ResponseEntity<?> deleteArticuloById(Long id) {
        if(articuloService.deleteArticulo(id)){
            return ResponseEntity.ok(articuloService.deleteArticulo(id));
        } else{
            throw new ArticuloNotFoundException("El articulo con el id " + id + " no se encuentra en la base de datos.");
        }
    }

    @Override
    public ResponseEntity<List<ArticuloResponse>> getAllArticulos() {
        return ResponseEntity.ok(articuloService.getAllArticulos());
    }

    @Override
    public ResponseEntity<List<Articulo>> getAllArticulosByCategoria(String categoria) {
        return null;
    }

    @Override
    public ResponseEntity<ArticuloResponse> getArticuloById(Long id) {
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

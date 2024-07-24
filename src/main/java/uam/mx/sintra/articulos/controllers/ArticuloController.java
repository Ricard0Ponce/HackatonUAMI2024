package uam.mx.sintra.articulos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uam.mx.sintra.articulos.api.ApiArticulos;
import uam.mx.sintra.articulos.models.Articulo;
import uam.mx.sintra.articulos.models.InlineResponse204;
import uam.mx.sintra.articulos.services.ArticuloService;

import java.util.List;

@RestController
public class ArticuloController implements ApiArticulos {

    @Autowired
    private ArticuloService articuloService;


    @Override
    public ResponseEntity<InlineResponse204> deleteArticuloById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Articulo>> getAllArticulos() {
        return null;
    }

    @Override
    public ResponseEntity<List<Articulo>> getAllArticulosByCategoria(String categoria) {
        return null;
    }

    @Override
    public ResponseEntity<Articulo> getArticuloById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Articulo>> postArticulo(Articulo body) {
        return null;
    }

    @Override
    public ResponseEntity<Articulo> putArticuloById(Integer id, Articulo body) {
        return null;
    }
}

package uam.mx.sintra.articulos.services;

import org.springframework.stereotype.Service;
import uam.mx.sintra.articulos.models.Articulo;
import uam.mx.sintra.articulos.models.ArticuloRequest;
import uam.mx.sintra.articulos.models.ArticuloResponse;

import java.util.List;
import java.util.Optional;

@Service
public interface ArticuloService {
    public Optional<ArticuloResponse> createArticulo(ArticuloRequest articulo);
    public List<ArticuloResponse> getAllArticulos();
    public Optional<ArticuloResponse> getArticuloById(Long id);
}

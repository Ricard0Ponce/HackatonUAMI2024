package uam.mx.sintra.articulos.services;

import org.springframework.stereotype.Service;
import uam.mx.sintra.articulos.models.Articulo;
import uam.mx.sintra.articulos.models.ArticuloRequest;
import uam.mx.sintra.articulos.models.ArticuloResponse;

import java.util.List;
import java.util.Optional;

@Service
// Definimos una interfaz que sera implementada en ArticuloServiceImpl, este es el contrato que se va a seguir.
// Todas las funcionalidades (Metodos) deben ir declarados aqui.
public interface ArticuloService {
    public Optional<ArticuloResponse> createArticulo(ArticuloRequest articulo);
    public List<ArticuloResponse> getAllArticulos();
    public Optional<ArticuloResponse> getArticuloById(Long id);
    public Optional<ArticuloResponse> updateArticulo(Long id, ArticuloRequest articulo);
    public void deleteArticulo(Long id);
}

package uam.mx.sintra.articulos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uam.mx.sintra.articulos.repositories.ArticuloRepository;

@Service
public class ArticuloServiceImpl {

    @Autowired
    private ArticuloRepository articuloRepository;
}

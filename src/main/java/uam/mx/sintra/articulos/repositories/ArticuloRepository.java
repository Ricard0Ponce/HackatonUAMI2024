package uam.mx.sintra.articulos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uam.mx.sintra.articulos.models.Articulo;

@Repository
public interface ArticuloRepository extends MongoRepository<Articulo, Long> {

}

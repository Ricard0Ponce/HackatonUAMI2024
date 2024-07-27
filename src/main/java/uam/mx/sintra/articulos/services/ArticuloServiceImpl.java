package uam.mx.sintra.articulos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uam.mx.sintra.articulos.exceptions.ListEmptyException;
import uam.mx.sintra.articulos.exceptions.RequestException;
import uam.mx.sintra.articulos.mappers.ArticuloMapper;
import uam.mx.sintra.articulos.models.Articulo;
import uam.mx.sintra.articulos.models.ArticuloRequest;
import uam.mx.sintra.articulos.models.ArticuloResponse;
import uam.mx.sintra.articulos.models.DatabaseSequence;
import uam.mx.sintra.articulos.repositories.ArticuloRepository;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
// Se implementa el contrato de ArticuloService
public class ArticuloServiceImpl implements ArticuloService {

    // Realizamos inyeccion de dependencias
    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    private MongoOperations mongoOperations;

    // Metodo para generar un id unico para cada articulo
    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    // Metodo que permitira la creacion de un articulo
    @Transactional
    @Override
    public Optional<ArticuloResponse> createArticulo(@Valid ArticuloRequest articulo) {
        try {
            // Mapear de ArticuloRequest a Articulo para poder guardarlo en la base de datos
            Articulo articuloEntity = ArticuloMapper.INSTANCE.toArticulo(articulo);
            // Se le asigna el id al objeto que se va a guardar
            articuloEntity.setId(generateSequence(Articulo.SEQUENCE_NAME));
            // Se guarda el articulo en la base de datos
            return Optional.of(articuloRepository.save(articuloEntity))
                    .map(ArticuloMapper.INSTANCE::toArticuloResponse); // Con map se mapea de Articulo a ArticuloResponse para devolver la respuesta
        } catch (RequestException e){
            throw new RuntimeException("Uno de los atributos es nulo.");
        }

    }


    // Metodo que permitira obtener todos los articulos
    @Transactional(readOnly = true) // Indicamos que esta funcion es de solo lectura
    @Override
    public List<ArticuloResponse> getAllArticulos() {
        if(!articuloRepository.findAll().isEmpty()){
            return articuloRepository.findAll() // Obtenemos todos los articulos
                    .stream() // Convertimos la lista a un stream
                    .map(ArticuloMapper.INSTANCE::toArticuloResponse) // Mapeamos de Articulo a ArticuloResponse
                    .collect(Collectors.toList());  // Convertimos el stream a una lista
        }
        else{
            throw new ListEmptyException("No se encontraron elementos dentro de la lista");
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ArticuloResponse> getArticuloById(Long id) {
        if(articuloRepository.findById(id).isPresent()){
            return articuloRepository.findById(id) // Obtenemos el articulo por medio del id
                    .map(ArticuloMapper.INSTANCE::toArticuloResponse); // Mapeamos de Articulo a ArticuloResponse
        } else{
            throw new RuntimeException("El articulo buscado no fue encontrado.");
        }
    }


    @Transactional
    @Override
    public Optional<ArticuloResponse> updateArticulo(Long id, ArticuloRequest articulo) {
            Articulo articuloEntity = ArticuloMapper.INSTANCE.toArticulo(articulo); // Mapeamos de ArticuloRequest a Articulo
            articuloEntity.setId(id); // Asignamos el id al articulo
            return Optional.of(articuloRepository.save(articuloEntity)) // Guardamos el articulo en la base de datos
                    .map(ArticuloMapper.INSTANCE::toArticuloResponse); // Mapeamos de Articulo a ArticuloResponse
    }

    @Override
    public Map<String, String> deleteArticulo(Long id) {
        if(articuloRepository.findById(id).isPresent()){
            articuloRepository.deleteById(id);
            Map<String, String> res = new HashMap<>();
            res.put("respuesta:","Se ha eliminado el elemento correctamente");
            return res;
        }
        else{
            throw new RuntimeException("El elemento no fue encontrado, por lo tanto no se elimino");
        }
    }

}

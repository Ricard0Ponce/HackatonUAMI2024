package uam.mx.sintra.articulos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uam.mx.sintra.articulos.exceptions.ArticuloNotFoundException;
import uam.mx.sintra.articulos.mappers.ArticuloMapper;
import uam.mx.sintra.articulos.models.Articulo;
import uam.mx.sintra.articulos.models.ArticuloRequest;
import uam.mx.sintra.articulos.models.ArticuloResponse;
import uam.mx.sintra.articulos.models.DatabaseSequence;
import uam.mx.sintra.articulos.repositories.ArticuloRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

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
    public Optional<ArticuloResponse> createArticulo(ArticuloRequest articulo) {
        // Mapear de ArticuloRequest a Articulo para poder guardarlo en la base de datos
        Articulo articuloEntity = ArticuloMapper.INSTANCE.toArticulo(articulo);
        // Se le asigna el id al objeto que se va a guardar
        articuloEntity.setId(generateSequence(Articulo.SEQUENCE_NAME));
        // Se guarda el articulo en la base de datos
        return Optional.of(articuloRepository.save(articuloEntity))
                .map(ArticuloMapper.INSTANCE::toArticuloResponse); // Con map se mapea de Articulo a ArticuloResponse para devolver la respuesta
    }

    // Metodo que permitira obtener todos los articulos
    @Transactional(readOnly = true) // Indicamos que esta funcion es de solo lectura
    @Override
    public List<ArticuloResponse> getAllArticulos() {
        return articuloRepository.findAll() // Obtenemos todos los articulos
                .stream() // Convertimos la lista a un stream
                .map(ArticuloMapper.INSTANCE::toArticuloResponse) // Mapeamos de Articulo a ArticuloResponse
                .collect(Collectors.toList());  // Convertimos el stream a una lista
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ArticuloResponse> getArticuloById(Long id) {
        return articuloRepository.findById(id) // Buscamos el articulo por su id
                .map(ArticuloMapper.INSTANCE::toArticuloResponse); // Mapeamos de Articulo a ArticuloResponse
    }

    @Transactional
    @Override
    public Optional<ArticuloResponse> updateArticulo(Long id, ArticuloRequest articulo) {
        if(articuloRepository.findById(id).isPresent()) { // Comenzamos verificando que el articulo exista por medio del ID.
            Articulo articuloEntity = ArticuloMapper.INSTANCE.toArticulo(articulo); // Mapeamos de ArticuloRequest a Articulo
            articuloEntity.setId(id); // Asignamos el id al articulo
            return Optional.of(articuloRepository.save(articuloEntity)) // Guardamos el articulo en la base de datos
                    .map(ArticuloMapper.INSTANCE::toArticuloResponse); // Mapeamos de Articulo a ArticuloResponse
        }
        return Optional.empty(); // En caso de que no se encuentre el articulo, se regresa un Optional vacio
    }

    @Override
    public void deleteArticulo(Long id) {

    }
}

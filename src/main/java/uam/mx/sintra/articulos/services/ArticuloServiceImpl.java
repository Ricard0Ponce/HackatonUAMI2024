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
public class ArticuloServiceImpl implements ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    private MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }


    @Override
    public Optional<ArticuloResponse> createArticulo(ArticuloRequest articulo) {
        // Mapear de ArticuloRequest a Articulo para poder guardarlo en la base de datos
        Articulo articuloEntity = ArticuloMapper.INSTANCE.toArticulo(articulo);
        articuloEntity.setId(generateSequence(Articulo.SEQUENCE_NAME));
        return Optional.of(articuloRepository.save(articuloEntity))
                .map(ArticuloMapper.INSTANCE::toArticuloResponse);
    }

    @Transactional
    @Override
    public List<ArticuloResponse> getAllArticulos() {
        return articuloRepository.findAll()
                .stream()
                .map(ArticuloMapper.INSTANCE::toArticuloResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ArticuloResponse> getArticuloById(Long id) {
        return articuloRepository.findById(id)
                .map(ArticuloMapper.INSTANCE::toArticuloResponse);
    }
}

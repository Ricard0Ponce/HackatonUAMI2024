package uam.mx.sintra.articulos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uam.mx.sintra.articulos.models.Articulo;
import uam.mx.sintra.articulos.models.ArticuloRequest;
import uam.mx.sintra.articulos.models.ArticuloResponse;


@Mapper
public interface ArticuloMapper {

    ArticuloMapper INSTANCE = Mappers.getMapper(ArticuloMapper.class);

    Articulo toArticulo(ArticuloRequest articuloRequest);
    Articulo toArticuloRP(ArticuloResponse articuloResponse);

    ArticuloRequest toArticuloRequest(Articulo articulo);
    ArticuloRequest toArticuloResp(ArticuloResponse articuloResponse);

    ArticuloResponse toArticuloResponseRQ(ArticuloRequest articuloRequest);
    ArticuloResponse toArticuloResponse(Articulo articulo);
}

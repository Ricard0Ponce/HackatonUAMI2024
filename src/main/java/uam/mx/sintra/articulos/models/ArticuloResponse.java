package uam.mx.sintra.articulos.models;

import javax.validation.constraints.NotNull;

//Este record nos permite obtener los datos de un articulo para poder mostrarlo
public record ArticuloResponse(
        Long id,
        String titulo,
        String contenido,
        String descripcion,
        String categoria,
        String foto,
        String requisitos
) {

}

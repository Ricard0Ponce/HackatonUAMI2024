package uam.mx.sintra.articulos.models;

//Este record nos permite obtener los datos de un articulo para poder mostrarlo
public record ArticuloResponse(
        String titulo,
        String contenido,
        String descripcion,
        String categoria,
        String foto,
        String requisitos
) {

}

package uam.mx.sintra.articulos.models;

import javax.validation.constraints.NotNull;

// Este record nos permitira obtener los datos de un articulo para poder crearlo
public record ArticuloRequest(
        Long id,
        @NotNull(message="El titulo del articulo no puede ser nulo.")
        String titulo,
        @NotNull(message="El contenido del articulo no puede ser nulo.")
        String contenido,
        @NotNull(message="La descripcion del articulo no puede ser nula.")
        String descripcion,
        @NotNull(message="La categoria del articulo no puede ser nula.")
        String categoria,
        String foto,
        @NotNull(message="Los requisitos del articulo no pueden ser nulos.")
        String requisitos
){
}

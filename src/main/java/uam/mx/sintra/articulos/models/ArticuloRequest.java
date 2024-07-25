package uam.mx.sintra.articulos.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
// Este record nos permitira obtener los datos de un articulo para poder crearlo
public record ArticuloRequest(
        @NotNull(message="El titulo del articulo no puede ser nulo.")
        String titulo,
        @NotNull(message="El contenido del articulo no puede ser nulo.")
        String contenido,
        @NotNull(message="La descripcion del articulo no puede ser nula.")
        String descripcion,
        String categoria,
        String foto,
        String requisitos
){
}

package uam.mx.sintra.articulos.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public record ArticuloRequest(
        @NotNull(message="El titulo del articulo no puede ser nulo.")
        String titulo,
        @NotNull(message="El contenido del articulo no puede ser nulo.")
        String contenido,
        @NotNull(message="La descripcion del articulo no puede ser nula.")
        String descripcion,
        CategoriaEnum categoriaEnum,
        String foto,
        String requisitos
) {
        public enum CategoriaEnum {
                ANALISIS_MEDICO,

                ESTUDIOS_MEDICOS,

                SALUD_MENTAL_Y_EMOCIONAL,

                RECOMENDACIONES_MEDICAS,

                EJERCICIO_Y_ACTIVIDAD_FISICA
        }
}
